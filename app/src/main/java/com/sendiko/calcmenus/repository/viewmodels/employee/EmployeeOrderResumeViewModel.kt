package com.sendiko.calcmenus.repository.viewmodels.employee

import androidx.lifecycle.ViewModel
import com.sendiko.calcmenus.repository.preferences.AppPreferences
import com.sendiko.calcmenus.ui.screens.employee.order_resume.OrderResumeScreenEvent
import com.sendiko.calcmenus.ui.screens.employee.order_resume.OrderResumeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EmployeeOrderResumeViewModel(appPreferences: AppPreferences): ViewModel() {

    private val _state = MutableStateFlow(OrderResumeScreenState())
    val state = _state.asStateFlow()

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
            OrderResumeScreenEvent.OnPlaceOrder -> TODO()
        }
    }
}