package com.leo.android.project.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leo.android.project.data.model.LoginResponse
import com.leo.android.project.data.model.Response
import com.leo.android.project.databinding.FragmentLoginBinding
import com.leo.android.project.ui.base.BaseDaggerFragment
import com.leo.android.project.ui.base.dialog.DialogDismissListener
import com.leo.android.project.ui.base.livedata.FailureEvent
import com.leo.android.project.ui.base.livedata.LoadingEvent
import com.leo.android.project.ui.base.livedata.SuccessEvent
import javax.inject.Inject

class LoginFragment: BaseDaggerFragment(){

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory

    val viewModel by lazy{
        ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater).apply {
            model = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventObserver()
    }

    private fun eventObserver(){
        viewModel.events().observe(viewLifecycleOwner, Observer {
            when (it) {
                is LoadingEvent<*> -> {
                    showLoader()
                }
                is SuccessEvent<*> -> {
                    it.data as LoginResponse

                    dismissLoader()
                    val accessCode = it.data.accessCode
                    showMessageDialog(accessCode,"OK", object : DialogDismissListener {
                        override fun onDismiss() {
                            val action = LoginFragmentDirections.actionLoginFragmentToGenreFragment(accessCode)
                            findNavController().navigate(action)
                        }
                    })
                }
                is FailureEvent<*> -> {
                    it.data as Response

                    dismissLoader()
                    showMessageDialog(it.data.message, "OK")
                }
            }
        })
    }
}