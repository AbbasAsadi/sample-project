package com.baseProject.android.ui.login.fragments.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.baseProject.android.R
import com.baseProject.android.data.Status
import com.baseProject.android.data.remote.model.requestModel.signup.SignupRequest
import com.baseProject.android.databinding.FragmentSignupBinding
import com.baseProject.android.ui.baseFragments.BaseFragment
import com.baseProject.android.ui.error.ErrorDialogFragment
import com.baseProject.android.ui.main.MainActivity
import com.baseProject.android.util.MessageMap
import com.baseProject.android.util.ValidateUtil


class SignupFragment : BaseFragment(), ErrorDialogFragment.OnErrorActionListener {
    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignupViewModel::class.java)
        binding.loadingObservable = viewModel.loading

        binding.doneButton.setOnClickListener {
            val name = binding.nameEditText.text
            val email = binding.emailEditText.text
            val password = binding.passwordEditText.text
            if (areInputsValid(name, email, password)) {
                val body = SignupRequest(
                    name.toString(),
                    email.toString(),
                    password.toString()
                )
                viewModel.signup(body)
            }
        }
        binding.loginTextView.setOnClickListener {
            back()
        }
        onBackPressed()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
    }

    private fun subscribe() {
        viewModel.response.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                }
                Status.ERROR, Status.SERVER_ERROR -> {
                    MessageMap.showMessage(requireActivity(), it.serverErrorCodes, requireContext())
                }
                Status.CONNECTION_ERROR -> {
                    ErrorDialogFragment.showError(childFragmentManager, it, this)
                }
                else -> true
            }

        }
    }


    private fun areInputsValid(
        name: CharSequence?,
        email: CharSequence?,
        password: CharSequence?
    ): Boolean {
        var isValid = true

        if (!ValidateUtil.commonValidator(name)) {
            isValid = false
            binding.nameTextLayout.error =
                getString(
                    R.string.value_can_not_be_blank,
                    getString(R.string.common_name)
                )
        }
        if (!ValidateUtil.commonValidator(email)) {
            isValid = false
            binding.emailTextLayout.error = getString(
                R.string.value_can_not_be_blank,
                getString(
                    R.string.email
                )
            )
        } else if (!ValidateUtil.isValidEmail(email)) {
            isValid = false
            binding.emailTextLayout.error = "Email is not valid"
        }

        if (!ValidateUtil.commonValidator(password)) {
            isValid = false
            binding.passwordTextLayout.error =
                getString(
                    R.string.value_can_not_be_blank,
                    getString(
                        R.string.password
                    )
                )
        }
        return isValid
    }


    private fun onBackPressed() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    back()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
    }

    private fun back() {
        navController.popBackStack()
    }

    override fun onErrorRetryButtonClick() {

    }

    override fun onErrorCancelButtonClick() {
    }
}