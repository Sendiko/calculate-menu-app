package com.sendiko.calcmenus.resto.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.calcmenus.core.preferences.AppPreferences
import com.sendiko.calcmenus.core.utils.FailedState
import com.sendiko.calcmenus.core.utils.LoginState
import com.sendiko.calcmenus.resto.auth.login.data.RestoLoginRequest
import com.sendiko.calcmenus.resto.auth.login.data.RestoLoginResponse
import com.sendiko.calcmenus.resto.core.RestoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RestoLoginViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val repo: RestoRepository
): ViewModel() {

    private val _state = MutableStateFlow(RestoLoginScreenState())
    val state = _state.asStateFlow()

    private fun restoLogin(restoLoginRequest: RestoLoginRequest){
        _state.update { it.copy(isLoading = true) }
        val request = repo.restoLogin(restoLoginRequest)
        request.enqueue(
            object : Callback<RestoLoginResponse>{
                override fun onResponse(
                    call: Call<RestoLoginResponse>,
                    response: Response<RestoLoginResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        401 -> {
                            _state.update {
                                it.copy(
                                    failedState = FailedState(
                                        isFailed = true,
                                        failedMessage = "Password not matched."
                                    )
                                )
                            }
                        }

                        404 -> {
                            _state.update {
                                it.copy(
                                    failedState = FailedState(
                                        isFailed = true,
                                        failedMessage = "Account not found."
                                    )
                                )
                            }
                        }

                        422 -> {
                            _state.update {
                                it.copy(
                                    failedState = FailedState(
                                        isFailed = true,
                                        failedMessage = "Unprocessable data."
                                    )
                                )
                            }
                        }

                        200 -> {
                            viewModelScope.launch {
                                response.body()?.token?.let { token ->
                                    appPreferences.saveLoginState(
                                        loginState = LoginState.RestaurantAccount.account,
                                        token = token
                                    )
                                    _state.update {
                                        it.copy(
                                            loginSuccessful = true
                                        )
                                    }
                                }
                            }
                        }

                        else -> _state.update {
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

                override fun onFailure(call: Call<RestoLoginResponse>, t: Throwable) {
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

    fun onEvent(event: RestoLoginScreenEvent){
        when(event){
            RestoLoginScreenEvent.OnLoginClick -> {
                val restoLoginRequest = RestoLoginRequest(
                    email = state.value.email,
                    password = state.value.password
                )
                restoLogin(restoLoginRequest)
            }
            is RestoLoginScreenEvent.OnEmailInput -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        failedState = FailedState(isFailed = false)
                    )
                }
            }
            is RestoLoginScreenEvent.OnPasswordInput -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        failedState = FailedState(isFailed = false)
                    )
                }
            }
            is RestoLoginScreenEvent.OnPasswordVisibilityToggle -> {
                _state.update {
                    it.copy(
                        isPasswordVisible = event.isVisible,
                    )
                }
            }
        }
    }
}