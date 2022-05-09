package com.baseProject.android.ui.login.fragments.login

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.baseProject.android.R
import com.baseProject.android.data.Status
import com.baseProject.android.data.remote.model.requestModel.login.LoginRequest
import com.baseProject.android.databinding.FragmentLoginBinding
import com.baseProject.android.ui.baseFragments.BaseFragment
import com.baseProject.android.ui.error.ErrorDialogFragment
import com.baseProject.android.ui.main.MainActivity
import com.baseProject.android.util.MessageMap
import com.baseProject.android.util.ValidateUtil


class LoginFragment : BaseFragment(), ErrorDialogFragment.OnErrorActionListener {
    private var sBackPressed: Long = 0
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        binding.loadingObservable = viewModel.loading

        binding.signupTextView.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_signupFragment)
        }
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text
            val password = binding.passwordEditText.text
            if (areInputsValid(email, password)) {
                val body = LoginRequest(
                    email.toString(),
                    password.toString()
                )
                viewModel.login(body)
            }
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


    private fun areInputsValid(email: CharSequence?, password: CharSequence?): Boolean {
        var isValid = true

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
                    val title = TextView(activity)
                    title.text = getString(R.string.exit_app_dialog_title)
                    title.setPadding(20, 20, 70, 20)
                    title.gravity = Gravity.START
                    title.textSize = 18f
                    title.setTextColor(Color.WHITE)

                    if (sBackPressed + 1000 > System.currentTimeMillis()) {
                        AlertDialog.Builder(
                            activity,
                            android.R.style.Theme_Material_Dialog_Alert
                        )
                            .setCustomTitle(title)
                            .setPositiveButton(getString(R.string.common_yes)) { _, _ ->
                                ActivityCompat.finishAffinity(requireActivity())
                            }
                            .setNegativeButton(getString(R.string.common_cancel)) { _, _ ->
                                sBackPressed = 0
                            }
                            .setCancelable(false)
                            .show()
                    } else
                        Toast.makeText(
                            activity,
                            getString(R.string.press_back_again),
                            Toast.LENGTH_SHORT
                        ).show()
                    sBackPressed = System.currentTimeMillis()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
    }

    override fun onErrorRetryButtonClick() {

    }

    override fun onErrorCancelButtonClick() {
    }

}