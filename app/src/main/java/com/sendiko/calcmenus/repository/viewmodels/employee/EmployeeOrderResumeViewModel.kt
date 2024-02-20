package com.sendiko.calcmenus.repository.viewmodels.employee

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.calcmenus.remote.requests.PostOrderRequest
import com.sendiko.calcmenus.remote.responses.PostOrderResponse
import com.sendiko.calcmenus.repository.EmployeeRepository
import com.sendiko.calcmenus.repository.preferences.AppPreferences
import com.sendiko.calcmenus.ui.screens.employee.order_resume.OrderResumeScreenEvent
import com.sendiko.calcmenus.ui.screens.employee.order_resume.OrderResumeScreenState
import com.sendiko.calcmenus.ui.utils.FailedState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeOrderResumeViewModel(appPreferences: AppPreferences): ViewModel() {

    private val _state = MutableStateFlow(OrderResumeScreenState())
    private val _token = appPreferences.getToken()
    val state = combine(_state, _token) { state, token ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), OrderResumeScreenState())

    private val employeeRepository = EmployeeRepository()
    private fun postOrder(){
        _state.update { it.copy(isLoading = true) }
        val request = employeeRepository
            .postOrder(
                token = "Bearer ${state.value.token}",
                request = PostOrderRequest(
                    tableNumber = state.value.tableName,
                    menuNotes = state.value.orderedMenuNoteList,
                    menuIds = state.value.orderedMenuList.map { menusItem ->
                        menusItem.id.toString()
                    },
                )
        )
        request.enqueue(
            object : Callback<PostOrderResponse> {
                override fun onResponse(
                    call: Call<PostOrderResponse>,
                    response: Response<PostOrderResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when(response.code()){
                        200 -> _state.update {
                            it.copy(isSuccess = true)
                        }
                        500 -> _state.update {
                            it.copy(
                                failedState = FailedState(
                                    isFailed = true,
                                    failedMessage = "Server error."
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<PostOrderResponse>, t: Throwable) {
                    _state.update {
                        it.copy(
                            failedState = FailedState(
                                isFailed = true,
                                failedMessage = t.message
                            )
                        )
                    }
                }

            }
        )
    }

    fun onEvent(event: OrderResumeScreenEvent){
        when(event){
            is OrderResumeScreenEvent.OnLoadList -> {
                _state.update { it.copy(
                    orderedMenuList = event.menuList
                ) }
            }
            is OrderResumeScreenEvent.OnAddMenu -> {
                _state.update { it.copy(
                    orderedMenuList = it.orderedMenuList + event.menu
                ) }
            }
            is OrderResumeScreenEvent.OnRemoveMenu -> {
                _state.update { it.copy(
                    orderedMenuList = it.orderedMenuList - event.menu
                ) }
            }
            is OrderResumeScreenEvent.OnAddNotes -> {
                _state.update { it.copy(
                    orderedMenuNoteList = it.orderedMenuNoteList + event.note
                ) }
            }
            is OrderResumeScreenEvent.OnInputTableName -> {
                _state.update { it.copy(
                    tableName = event.tableName
                ) }
            }
            OrderResumeScreenEvent.OnPlaceOrder -> postOrder()
            OrderResumeScreenEvent.OnClearMenu -> {
                Log.i("OnClearMenu", "onEvent: test")
                _state.update { it.copy(
                    orderedMenuList = emptyList()
                ) }
            }
        }
    }
}