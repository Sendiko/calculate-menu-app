package com.sendiko.calcmenus.employee.profile.presentation

import android.net.Uri
import com.sendiko.calcmenus.ui.utils.FailedState

data class EmployeeProfileScreenState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val imageUri: Uri? = null,
    val isLoading: Boolean = false,
    val isEditable: Boolean = false,
    val logoutSuccessful: Boolean = false,
    val failedState: FailedState = FailedState(),
)
