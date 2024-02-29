package com.sendiko.calcmenus.splash.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.calcmenus.core.preferences.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(appPreferences: AppPreferences): ViewModel() {

    private val _state = MutableStateFlow(SplashScreenState())
    private val _isLoggedIn = appPreferences.getLoginState()
    private val _token = appPreferences.getToken()
    val state = combine(_state, _isLoggedIn, _token){ state, isLoggedIn, token ->
        Log.i("LOGIN_STATE", "state in SplashScreenViewModel: $isLoggedIn")
        Log.i("LOGIN_STATE", "token check: $token")
        state.copy(isLoggedIn = isLoggedIn)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), SplashScreenState())

}