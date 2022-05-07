package com.baseProject.android.ui.login.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.baseProject.android.databinding.FragmentLoginBinding
import com.baseProject.android.ui.baseFragments.BaseFragment

class LoginFragment : BaseFragment() {
    private var sBackPressed: Long = 0
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        return binding.root
    }

}