package com.sendiko.calcmenus.repository.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sendiko.calcmenus.repository.preferences.AppPreferences
import com.sendiko.calcmenus.employee.login.presentation.EmployeeLoginViewModel
import com.sendiko.calcmenus.employee.menu.presentation.EmployeeMenuViewModel
import com.sendiko.calcmenus.employee.order_resume.presentation.EmployeeOrderResumeViewModel
import com.sendiko.calcmenus.employee.profile.presentation.EmployeeProfileViewModel
import com.sendiko.calcmenus.resto.auth.login.presentation.RestoLoginViewModel
import com.sendiko.calcmenus.resto.profile.presentation.RestoProfileViewModel

class ViewModelFactory private constructor(private val appPreferences: AppPreferences) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashScreenViewModel::class.java) ->
                SplashScreenViewModel(appPreferences) as T
            modelClass.isAssignableFrom(RestoLoginViewModel::class.java) ->
                RestoLoginViewModel(appPreferences) as T
            modelClass.isAssignableFrom(EmployeeLoginViewModel::class.java) ->
                EmployeeLoginViewModel(appPreferences) as T
            modelClass.isAssignableFrom(RestoProfileViewModel::class.java) ->
                RestoProfileViewModel(appPreferences) as T
            modelClass.isAssignableFrom(EmployeeProfileViewModel::class.java) ->
                EmployeeProfileViewModel(appPreferences) as T
            modelClass.isAssignableFrom(EmployeeMenuViewModel::class.java) ->
                EmployeeMenuViewModel(appPreferences) as T
            modelClass.isAssignableFrom(EmployeeOrderResumeViewModel::class.java) ->
                EmployeeOrderResumeViewModel(appPreferences) as T
            else -> throw IllegalArgumentException("Unknown modelClass: " + modelClass.name)
        }
    }

        companion object{
            @Volatile
            var INSTANCE: ViewModelFactory?= null

            @JvmStatic
            fun getInstance(appPreferences: AppPreferences): ViewModelFactory {
                when(INSTANCE){
                    null -> synchronized(ViewModelFactory::class.java){
                        INSTANCE = ViewModelFactory(appPreferences)
                    }
                }
                return INSTANCE as ViewModelFactory
            }

        }
}