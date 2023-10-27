package com.sendiko.calcmenus.repository.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sendiko.calcmenus.repository.preferences.AppPreferences
import com.sendiko.calcmenus.repository.viewmodels.employee.EmployeeLoginViewModel
import com.sendiko.calcmenus.repository.viewmodels.employee.EmployeeProfileViewModel
import com.sendiko.calcmenus.repository.viewmodels.resto.RestoLoginViewModel
import com.sendiko.calcmenus.repository.viewmodels.resto.RestoProfileViewModel

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