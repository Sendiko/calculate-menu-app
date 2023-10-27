package com.sendiko.calcmenus.ui.screens.employee.profile

import android.net.Uri

sealed interface EmployeeProfileScreenEvent{
    data class OnNameEdit(val name: String): EmployeeProfileScreenEvent
    data class OnEmailEdit(val email: String): EmployeeProfileScreenEvent
    data class OnPasswordEdit(val password: String): EmployeeProfileScreenEvent
    data class OnPasswordVisibilityToggle(val isVisible: Boolean): EmployeeProfileScreenEvent
    data class OnUpdateImage(val imageUri: Uri?): EmployeeProfileScreenEvent
    data class OnSetEditable(val editable: Boolean): EmployeeProfileScreenEvent
    object OnLogoutClick: EmployeeProfileScreenEvent
}