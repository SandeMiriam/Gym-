package com.devbenadate.workout.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.devbenadate.workout.ApiClient
import com.devbenadate.workout.ApiInterface
import com.devbenadate.workout.R
import com.devbenadate.workout.models.LoginRequest
import com.devbenadate.workout.models.LoginResponsse
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var sharedPrefs: SharedPreferences
    lateinit var tvSignUp:TextView
    lateinit var etEmail: TextInputEditText
    lateinit var etPassword:TextInputEditText
    lateinit var tilEmail:TextInputLayout
    lateinit var tilPassword:TextInputLayout
    lateinit var btnLogin:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPrefs = getSharedPreferences("WORKOUT_PREFS", MODE_PRIVATE)
        tvSignUp=findViewById(R.id.tvSignup)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        tilEmail=findViewById(R.id.tilEmail)
        tilPassword=findViewById(R.id.tilPassword)
        btnLogin=findViewById(R.id.btnLogin)


        tvSignUp.setOnClickListener {
            val intent=Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val intent=Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }


    }
    fun validateLogin(){
        var email=etEmail.text.toString()
        var password=etPassword.text.toString()
        var error=false
        if(email.isBlank()){
            error=true
            tilEmail.error="Email is required"
        }
        if (password.isBlank()){
            error=true
            tilPassword.error="Password is required"
        }
        if (!error){
            var loginRequest = LoginRequest(email, password)
            makeloginRequest(loginRequest)
        }
    }

    fun makeloginRequest(loginRequest: LoginRequest){
        val apiClient= ApiClient.buildApiClient(ApiInterface::class.java)
        val request= apiClient.loginUser(loginRequest)

        request.enqueue(object : Callback<LoginResponsse> {
            override fun onResponse(call: Call<LoginResponsse>, response: Response<LoginResponsse>) {
                if (response.isSuccessful){
                    val loginResponsse= response.body()
                    persistLoginDetails(loginResponsse!!)
                    Toast.makeText(baseContext,loginResponsse?.message,Toast.LENGTH_LONG).show()

                    startActivity(Intent(baseContext,HomePageActivity::class.java))
                    finish()
                }
                else{
                    val error=response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }


            override fun onFailure(call: Call<LoginResponsse>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }

        })
    }
    fun persistLoginDetails(LoginResponse: LoginResponsse){
        val editor=sharedPrefs.edit()
        editor.putString("USER_ID",LoginResponse.userId)
        editor.putString("ACCESS_TOKEN",LoginResponse.accesstoken)
        editor.putString("PROFILE_ID",LoginResponse.userId)
        editor.apply()
    }
}