package com.devbenadate.workout.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.devbenadate.workout.ApiClient
import com.devbenadate.workout.ApiInterface
import com.devbenadate.workout.RegisterRequest
import com.devbenadate.workout.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            validateSignup()
        }
        binding.tvSignin.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    fun validateSignup() {
        var name = binding.etFirstname.text.toString()
        var second = binding.etSecondname.text.toString()
        var confirm = binding.etConfirmpassword.text.toString()
        var password = binding.etPassword.text.toString()
        var phoneNumber = binding.tvPhonumber.text.toString()
        var email=binding.etEmail.text.toString()
         var error=false

        if (name.isBlank()) {
            binding.tilFirstname.error = "Firstname required"
        }
        if (second.isBlank()) {
            binding.tilSecondname.error = "second name required"
        }
        if (confirm.isBlank()) {
            binding.tilConfirmpassword.error = "confirm password"
        }
        else {
            binding.tilConfirmpassword.error = null
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "enter password"
        }
        if (phoneNumber.isBlank()) {
            binding.textInputLayout.error = "enter password"
        }
        if (email.isBlank()){
            binding.tilEmail.error="input email"
        }
        else
        {
            var error = false

            if (!error) {
                var registerRequest = RegisterRequest(name, second, phoneNumber, email, password)
            }

        }


        fun makeRegisterationRequest(registerRequest: RegisterRequest) {
            var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

        }

    }

}
