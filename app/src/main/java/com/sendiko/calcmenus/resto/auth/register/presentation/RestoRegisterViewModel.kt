package com.sendiko.calcmenus.resto.auth.register.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sendiko.calcmenus.resto.auth.register.data.RestoRegisterRequest
import com.sendiko.calcmenus.resto.auth.register.data.RestoRegisterResponse
import com.sendiko.calcmenus.resto.core.RestoRepository
import com.sendiko.calcmenus.ui.utils.FailedState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestoRegisterViewModel(private val repo: RestoRepository) : ViewModel() {

    private val _state = MutableStateFlow(RestoRegisterState())
    val state = _state.asStateFlow()

    private fun restoRegister(
        registerRequest: RestoRegisterRequest
    ) {
        _state.update { it.copy(isLoading = true) }
        val request = repo.restoRegister(registerRequest)
        request.enqueue(
            object : Callback<RestoRegisterResponse> {
                override fun onResponse(
                    call: Call<RestoRegisterResponse>,
                    response: Response<RestoRegisterResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    Log.i("RESPONSE_BODY", "response: ${response.body()}")
                    when (response.code()) {
                        422 -> {
                            _state.update {
                                it.copy(
                                    failedState = FailedState(
                                        isFailed = true,
                                        failedMessage = "Please check your data."
                                    )
                                )
                            }
                        }

                        201 -> {
                            _state.update {
                                it.copy(loginSuccessful = true)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<RestoRegisterResponse>, t: Throwable) {
                    _state.update {
                        it.copy(
                            failedState = FailedState(
                                isFailed = true,
                                failedMessage = t.message
                            ),
                            isLoading = false
                        )
                    }
                }

            }
        )
    }

    fun onEvent(event: RestoRegisterEvent) {
        when (event) {
            RestoRegisterEvent.OnClickRegister -> {
                restoRegister(
                    RestoRegisterRequest(
                        name = state.value.restoName,
                        address = state.value.restoAddress,
                        phoneContact = state.value.restoPhone,
                        email = state.value.email,
                        password = state.value.password,
                        passwordConfirmation = state.value.password
                    )
                )
            }

            is RestoRegisterEvent.OnEmailInput -> _state.update {
                it.copy(email = event.email, failedState = FailedState(isFailed = false))
            }

            is RestoRegisterEvent.OnPasswordInput -> _state.update {
                it.copy(password = event.password, failedState = FailedState(isFailed = false))
            }

            is RestoRegisterEvent.OnRestoAddressInput -> _state.update {
                it.copy(restoAddress = event.restoAddress, failedState = FailedState(isFailed = false))
            }

            is RestoRegisterEvent.OnRestoNameInput -> _state.update {
                it.copy(restoName = event.restoName, failedState = FailedState(isFailed = false))
            }

            is RestoRegisterEvent.OnRestoPhoneInput -> _state.update {
                it.copy(restoPhone = event.restoPhone, failedState = FailedState(isFailed = false))
            }

            is RestoRegisterEvent.OnNavigatePart -> _state.update {
                it.copy(registerPart = event.page, failedState = FailedState(isFailed = false))
            }

            is RestoRegisterEvent.OnPasswordVisibilityToggle -> _state.update {
                it.copy(isPasswordVisible = event.isVisible)
            }
        }
    }
}