package com.sendiko.calcmenus.employee.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.calcmenus.employee.profile.data.EmployeeLogoutResponse
import com.sendiko.calcmenus.employee.core.EmployeeRepository
import com.sendiko.calcmenus.core.preferences.AppPreferences
import com.sendiko.calcmenus.core.utils.FailedState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeProfileViewModel(private val appPreferences: AppPreferences) : ViewModel() {

    private val repository = EmployeeRepository()
    private val _state = MutableStateFlow(EmployeeProfileScreenState())
    val state = _state.asStateFlow()

    private fun employeeLogout(token: String) {
        _state.update { it.copy(isLoading = true) }
        val request = repository.employeeLogout("Bearer $token")
        request.enqueue(
            object : Callback<EmployeeLogoutResponse> {
                override fun onResponse(
                    call: Call<EmployeeLogoutResponse>,
                    response: Response<EmployeeLogoutResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        401 -> {
                            _state.update {
                                it.copy(
                                    failedState = FailedState(
                                        isFailed = true,
                                        failedMessage = response.body()?.message
                                    )
                                )
                            }
                        }

                        200 -> viewModelScope.launch {
                            appPreferences.saveLoginState(
                                loginState = "",
                                token = ""
                            )
                            _state.update {
                                it.copy(
                                    logoutSuccessful = true
                                )
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<EmployeeLogoutResponse>, t: Throwable) {
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

    fun onEvent(event: EmployeeProfileScreenEvent) {
        when (event) {
            is EmployeeProfileScreenEvent.OnEmailEdit -> _state.update {
                it.copy(email = event.email)
            }

            is EmployeeProfileScreenEvent.OnNameEdit -> _state.update {
                it.copy(name = event.name)
            }

            is EmployeeProfileScreenEvent.OnPasswordEdit -> _state.update {
                it.copy(password = event.password)
            }

            is EmployeeProfileScreenEvent.OnPasswordVisibilityToggle -> _state.update {
                it.copy(isPasswordVisible = event.isVisible)
            }

            is EmployeeProfileScreenEvent.OnUpdateImage -> _state.update {
                it.copy(imageUri = event.imageUri)
            }

            is EmployeeProfileScreenEvent.OnSetEditable -> _state.update {
                it.copy(isEditable = event.editable)
            }

            is EmployeeProfileScreenEvent.OnLogoutClick -> viewModelScope.launch {
                appPreferences.getToken().collectIndexed { _, token ->
                    employeeLogout(token = token)
                }
            }
        }
    }
}