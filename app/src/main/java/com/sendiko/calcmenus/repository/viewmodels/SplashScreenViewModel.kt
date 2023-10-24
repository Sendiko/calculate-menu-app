package com.sendiko.calcmenus.repository.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.calcmenus.repository.preferences.AppPreferences
import com.sendiko.calcmenus.ui.screens.welcome.SplashScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SplashScreenViewModel(appPreferences: AppPreferences): ViewModel() {

    private val _state = MutableStateFlow(SplashScreenState())
    private val _isLoggedIn = appPreferences.getLoginState()
    private val _token = appPreferences.getToken()
    val state = combine(_state, _isLoggedIn, _token){ state, isLoggedIn, token ->
        Log.i("LOGIN_STATE", "state in SplashScreenViewModel: $isLoggedIn")
        Log.i("LOGIN_STATE", "token check: $token")
        state.copy(isLoggedIn = isLoggedIn)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), SplashScreenState())

}