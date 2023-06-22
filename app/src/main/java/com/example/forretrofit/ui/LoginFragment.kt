package com.example.forretrofit.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.forretrofit.R
import com.example.forretrofit.data.models.login.LoginRequestData
import com.example.forretrofit.databinding.FragmentLoginBinding
import com.example.forretrofit.presentation.loginviewmodel.LoginViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var pref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        pref = requireContext().getSharedPreferences("myPref", Context.MODE_PRIVATE)

        initObservers()

        fillData()

        initListeners()
    }

    private fun initListeners() {
        binding.btnSignin.setOnClickListener {
            login()
        }
        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            loginViewModel.loginResponseLiveData.observe(requireActivity()) {
                if (it.isSuccessful) {
                    pref.edit().putString("token", it.body()!!.loginPayload.token).apply()
                    pref.edit().putString("phone", it.body()!!.loginPayload.phone).apply()
                    pref.edit().putString("name", it.body()!!.loginPayload.name).apply()
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                } else {
                    Log.d(tag, it.errorBody().toString())
                }
                Toast.makeText(requireContext(), it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login() {
        val phone = binding.etPhone.text.toString()
        val password = binding.etPassword.text.toString()
        if (phone.isNotEmpty() && password.isNotEmpty()) {
            lifecycleScope.launch {
                loginViewModel.login(
                    LoginRequestData(
                        phone = phone,
                        password = password
                    )
                )
            }
        } else if (phone.isEmpty()) {
            binding.etPhone.error = "Please enter your phone number"
        } else binding.etPassword.error = "Please enter your password"
    }

    private fun fillData() {
        binding.etPhone.setText(pref.getString("phone", ""))
    }

}