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
import com.example.forretrofit.data.models.login.RegisterRequestData
import com.example.forretrofit.databinding.FragmentRegisterBinding
import com.example.forretrofit.presentation.loginviewmodel.LoginViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var pref: SharedPreferences
    private val loginViewModel: LoginViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        pref = requireContext().getSharedPreferences("myPref", Context.MODE_PRIVATE)

        initObservers()

        initListeners()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            loginViewModel.loginResponseLiveData.observe(requireActivity()) {
                if (it.isSuccessful) {
                    try {
                        Log.d(tag, it.body().toString())
                        pref.edit().putString("name", it.body()!!.loginPayload.name).apply()
                        pref.edit().putString("token", it.body()!!.loginPayload.token).apply()
                        pref.edit().putString("phone", it.body()!!.loginPayload.phone).apply()

                        Log.d(tag, pref.getString("token", "").toString())
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                } else {
                    Log.d(tag, it.errorBody().toString())
                }
                Toast.makeText(requireContext(), it.message(), Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun register() {
        val name = binding.etName.text.toString()
        val password = binding.etPassword.text.toString()
        val phone = binding.etPhone.text.toString()
        if (name.isNotEmpty() && password.isNotEmpty() && phone.isNotEmpty()) {
            lifecycleScope.launch {
                loginViewModel.register(
                    RegisterRequestData(
                        phone = phone,
                        name = name,
                        password = password
                    )
                )
            }
        } else if (name.isEmpty()) {
            binding.etName.error = "Please enter your name!"
        } else if (phone.isEmpty()) {
            binding.etPhone.error = "Please enter your phone number!"
        } else {
            binding.etPassword.error = "Please enter your password!"
        }
    }

    private fun initListeners() {

        binding.btnSignup.setOnClickListener {
            register()
        }

        binding.tvSignin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}