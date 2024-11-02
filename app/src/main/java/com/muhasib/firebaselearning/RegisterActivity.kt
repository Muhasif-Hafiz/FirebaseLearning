package com.muhasib.firebaselearning

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var et_name: EditText
    private lateinit var et_email: EditText
    private lateinit var et_password: EditText
    private lateinit var btnRegisterUser: Button
    private lateinit var auth : FirebaseAuth


    // Initialize FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth=FirebaseAuth.getInstance()


        et_name = findViewById(R.id.et_name)
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        btnRegisterUser = findViewById(R.id.btn_sign_up)

        btnRegisterUser.setOnClickListener {
            val name = et_name.text.toString()
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please Enter All the Details!", Toast.LENGTH_SHORT).show()
            } else {
                RegisterUser(name, email, password)
            }
        }
    }

    private fun RegisterUser(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

            if(it.isSuccessful){
                Toast.makeText(this, "User Registered Successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "Registration Failed!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}