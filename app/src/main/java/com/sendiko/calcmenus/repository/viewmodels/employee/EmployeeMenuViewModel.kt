package com.sendiko.calcmenus.repository.viewmodels.employee

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.calcmenus.remote.responses.GetMenuResponse
import com.sendiko.calcmenus.repository.EmployeeRepository
import com.sendiko.calcmenus.repository.preferences.AppPreferences
import com.sendiko.calcmenus.ui.screens.employee.menu_screen.MenuScreenEvent
import com.sendiko.calcmenus.ui.screens.employee.menu_screen.MenuScreenState
import com.sendiko.calcmenus.ui.utils.FailedState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeMenuViewModel(private val appPreferences: AppPreferences) : ViewModel() {

    private val repository = EmployeeRepository()

    private val _state = MutableStateFlow(MenuScreenState())
    private val _token = appPreferences.getToken()
    val state = combine(_state, _token) { state, token ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), MenuScreenState())

    private fun getMenuList(token: String) {
        _state.update { it.copy(isLoading = true) }
        val bearerToken = "Bearer $token"
        val request = repository.getMenu(bearerToken)
        request.enqueue(
            object : Callback<GetMenuResponse> {
                override fun onResponse(
                    call: Call<GetMenuResponse>,
                    response: Response<GetMenuResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        401 -> _state.update {
                            it.copy(
                                failedState = FailedState(
                                    isFailed = true,
                                    failedMessage = "Your session has ended, Please login again"
                                )
                            )
                        }

                        500 -> _state.update {
                            it.copy(
                                failedState = FailedState(
                                    isFailed = true,
                                    failedMessage = "Server error."
                                )
                            )
                        }

                        200 -> response.body()?.menus.let { menusItem ->
                            Log.i("MENU", "response: ${response.body()?.menus}")
                            _state.update {
                                it.copy(menuList = menusItem)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<GetMenuResponse>, t: Throwable) {
                    _state.update {
                        it.copy(
                            isLoading = false,
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

    fun onEvent(event: MenuScreenEvent) {
        when (event) {
            is MenuScreenEvent.OnAddMenuToList -> {
                _state.update {
                    it.copy(orderedMenuList = it.orderedMenuList + event.menu)
                }
                Log.i("ORDERED_MENU", "ordered menu: ${state.value.orderedMenuList}")
            }

            is MenuScreenEvent.OnRemoveMenuFromList -> _state.update {
                Log.i("ORDERED_MENU", "ordered menu: ${state.value.orderedMenuList}")
                it.copy(orderedMenuList = it.orderedMenuList - event.menu)
            }

            is MenuScreenEvent.OnSearchInput -> _state.update {
                it.copy(searchText = event.menuItem)
            }

            is MenuScreenEvent.OnSortToggle -> _state.update {
                it.copy(sortBy = event.sortBy)
            }

            is MenuScreenEvent.RequestMenuData -> viewModelScope.launch {
                getMenuList(state.value.token)
            }

            is MenuScreenEvent.SwitchPages -> _state.update {
                it.copy(currentPage = event.page)
            }

            MenuScreenEvent.OnPlaceOrder -> {

            }
        }
    }
}