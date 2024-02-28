package com.sendiko.calcmenus.resto.profile.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.calcmenus.resto.profile.data.RestoLogoutResponse
import com.sendiko.calcmenus.resto.core.RestoRepository
import com.sendiko.calcmenus.repository.preferences.AppPreferences
import com.sendiko.calcmenus.ui.utils.FailedState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestoProfileViewModel(private val appPreferences: AppPreferences) : ViewModel() {

    private val repository = RestoRepository()

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    private fun restoLogout(token: String) {
        _state.update { it.copy(isLoading = true) }
        val request = repository.restoLogout(token)
        request.enqueue(
            object : Callback<RestoLogoutResponse> {
                override fun onResponse(
                    call: Call<RestoLogoutResponse>,
                    response: Response<RestoLogoutResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        200 -> viewModelScope.launch {
                            appPreferences.saveLoginState(loginState = "", token = "")
                            _state.update {
                                it.copy(logoutSuccessful = true)
                            }
                        }

                        else -> {
                            _state.update {
                                it.copy(
                                    failedState = FailedState(
                                        isFailed = true,
                                        failedMessage = "Server error."
                                    )
                                )
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<RestoLogoutResponse>, t: Throwable) {
                    _state.update {
                        it.copy(
                            failedState = FailedState(
                                isFailed = true,
                                failedMessage = "Server error."
                            ),
                            isLoading = false
                        )
                    }
                }

            }
        )
    }

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.OnNameEdit -> _state.update {
                it.copy(restoName = event.name)
            }

            is ProfileScreenEvent.OnAddressEdit -> _state.update {
                it.copy(restoAddress = event.address)
            }

            is ProfileScreenEvent.OnPhoneEdit -> _state.update {
                it.copy(restoPhone = event.phone)
            }

            is ProfileScreenEvent.OnEmailEdit -> _state.update {
                it.copy(email = event.email)
            }

            is ProfileScreenEvent.OnPasswordEdit -> _state.update {
                it.copy(password = event.password)
            }

            is ProfileScreenEvent.OnEditButtonClick -> _state.update {
                it.copy(editable = event.editable)
            }

            is ProfileScreenEvent.OnUpdateImage -> _state.update {
                it.copy(imageUri = event.imageUri)
            }

            is ProfileScreenEvent.OnPasswordVisibilityClick -> _state.update {
                it.copy(isPasswordVisible = event.isVisible)
            }

            ProfileScreenEvent.OnLogoutClick -> viewModelScope.launch {
                appPreferences.getToken().collectIndexed { _, value ->
                    Log.i("TOKEN", "token: $value")
                    restoLogout("Bearer $value")
                }
            }
        }
    }
}