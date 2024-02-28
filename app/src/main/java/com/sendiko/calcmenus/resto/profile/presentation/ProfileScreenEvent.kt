package com.sendiko.calcmenus.resto.profile.presentation

import android.net.Uri

sealed interface ProfileScreenEvent {

    data class OnNameEdit(val name: String): ProfileScreenEvent
    data class OnAddressEdit(val address: String): ProfileScreenEvent
    data class OnPhoneEdit(val phone: String): ProfileScreenEvent
    data class OnEmailEdit(val email: String): ProfileScreenEvent
    data class OnPasswordEdit(val password: String): ProfileScreenEvent
    data class OnEditButtonClick(val editable: Boolean): ProfileScreenEvent
    data class OnUpdateImage(val imageUri: Uri): ProfileScreenEvent
    data class OnPasswordVisibilityClick(val isVisible: Boolean): ProfileScreenEvent
    object OnLogoutClick: ProfileScreenEvent
}