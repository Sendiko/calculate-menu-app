package com.sendiko.calcmenus.repository.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferences private constructor(private val dataStore: DataStore<Preferences>){

    private val loginStateKey = stringPreferencesKey("login_account")
    private val tokenKey = stringPreferencesKey("token")

    fun getLoginState(): Flow<String> {
        return dataStore.data.map {
            it[loginStateKey]?:""
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map {
            it[tokenKey]?:""
        }
    }

    suspend fun saveLoginState(loginState: String, token: String) {
         dataStore.edit {
             it[loginStateKey] = loginState
             it[tokenKey] = token
         }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): AppPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = AppPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}