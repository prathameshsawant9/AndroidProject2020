package com.leo.android.project.ui.login

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.leo.android.project.BR
import com.leo.android.project.data.repo.MainRepository
import com.leo.android.project.ui.base.BaseViewModel
import com.leo.android.project.ui.base.livedata.FailureEvent
import com.leo.android.project.ui.base.livedata.LoadingEvent
import com.leo.android.project.ui.base.livedata.SuccessEvent
import kotlinx.coroutines.launch

class LoginViewModel(val repo: MainRepository): BaseViewModel(){

    val user = UsernameObservable("")
    val pass = PasswordObservable("")

    fun validateLogin(){
        viewModelScope.launch(exceptionHandler) {
            if (user.username.isBlank()){
                user.usernameError = "Please enter username"
                return@launch
            }

            if (pass.password.isBlank()){
                pass.passwordError = "Please enter password"
                return@launch
            }

            notify(LoadingEvent(0, "Please wait..."))

            val loginResponse = repo.login(username = user.username, password = pass.password)

            if (loginResponse.statusCode == 200){
                notify(SuccessEvent(0, loginResponse))
            }else{
                notify(FailureEvent(0, loginResponse))
            }
        }
    }

    class UsernameObservable(private val name: String): BaseObservable(){

        @get:Bindable
        var username: String = name
            set(value) {
                field = value

                if(value.isNotBlank()) {
                    usernameError = null
                }

                notifyPropertyChanged(BR.username)

            }

        @get:Bindable
        var usernameError: String? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.usernameError)
            }
    }

    class PasswordObservable(private val pass: String): BaseObservable(){

        @get:Bindable
        var password: String = pass
            set(value) {
                field = value

                if(value.isNotEmpty()) {
                    passwordError = null
                }

                notifyPropertyChanged(BR.password)
            }

        @get:Bindable
        var passwordError: String? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.passwordError)
            }
    }
}