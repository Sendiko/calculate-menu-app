package com.sendiko.calcmenus.resto.profile.presentation

import android.net.Uri
import com.sendiko.calcmenus.ui.utils.FailedState

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val failedState: FailedState = FailedState(),
    val logoutSuccessful: Boolean = false,
    val restoName: String = "",
    val restoAddress: String = "",
    val restoPhone: String = "",
    val email: String = "",
    val password: String = "",
    val editable: Boolean = false,
    val imageUri: Uri? = null,
    val isPasswordVisible: Boolean = false
)
