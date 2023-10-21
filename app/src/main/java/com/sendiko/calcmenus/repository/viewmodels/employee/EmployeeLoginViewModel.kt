package com.sendiko.calcmenus.repository.viewmodels.employee

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sendiko.calcmenus.remote.requests.EmployeeLoginRequest
import com.sendiko.calcmenus.remote.responses.EmployeeLoginResponse
import com.sendiko.calcmenus.repository.EmployeeRepository
import com.sendiko.calcmenus.ui.screens.employee.login_screen.EmployeeLoginEvents
import com.sendiko.calcmenus.ui.screens.employee.login_screen.EmployeeLoginScreenState
import com.sendiko.calcmenus.ui.utils.FailedState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeLoginViewModel : ViewModel() {

    private val repository = EmployeeRepository()

    private val _state = MutableStateFlow(EmployeeLoginScreenState())
    val state = _state.asStateFlow()

    private fun postLogin(employeeLoginRequest: EmployeeLoginRequest) {
        _state.update { it.copy(isLoading = true) }
        val request = repository.employeeLogin(employeeLoginRequest)
        request.enqueue(
            object : Callback<EmployeeLoginResponse> {
                override fun onResponse(
                    call: Call<EmployeeLoginResponse>,
                    response: Response<EmployeeLoginResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    Log.i("RESPONSE_CODE", "responseCode: ${response.code()}")
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
                            _state.update {
                                it.copy(
                                    loginSuccessful = true
                                )
                            }
                        }

                        else -> _state.update {
                            it.copy(
                                failedState = FailedState(
                                    isFailed = true,
                                    failedMessage = "Server error."
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<EmployeeLoginResponse>, t: Throwable) {
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

    private fun validateInput(email: String, password: String): Boolean{
        return when{
            email.isEmpty() -> false
            password.isEmpty() -> false
            password.length < 6 -> false
            else -> true
        }
    }

    fun onEvent(events: EmployeeLoginEvents) {
        when (events) {
            is EmployeeLoginEvents.OnEmailInput -> _state.update {
                it.copy(email = events.email, failedState = FailedState(isFailed = false, failedMessage = ""))
            }

            is EmployeeLoginEvents.OnPasswordInput -> _state.update {
                it.copy(password = events.password, failedState = FailedState(isFailed = false, failedMessage = ""))
            }

            is EmployeeLoginEvents.OnClickLogin -> {
                val employeeLoginRequest =
                    EmployeeLoginRequest(email = state.value.email, password = state.value.password)
                if (validateInput(state.value.email, state.value.password)){
                    postLogin(employeeLoginRequest)
                } else _state.update {
                    it.copy(failedState = FailedState(isFailed = true, failedMessage = "Please check your data."))
                }
            }

            is EmployeeLoginEvents.PasswordVisibilityToggle -> _state.update {
                it.copy(
                    isPasswordVisible = !it.isPasswordVisible
                )
            }
        }
    }
}