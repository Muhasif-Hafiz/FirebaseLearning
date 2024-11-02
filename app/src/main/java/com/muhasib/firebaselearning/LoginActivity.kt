package com.muhasib.firebaselearning

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var et_email : EditText
    private lateinit var et_password :EditText
    private lateinit var btn_login : Button
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        et_email=findViewById(R.id.et_email_signIn)
        et_password=findViewById(R.id.et_password_signIn)
        btn_login=findViewById(R.id.btn_sign_in)
        auth=FirebaseAuth.getInstance()

        btn_login.setOnClickListener{
           var email=et_email.text.toString()
            var password= et_password.text.toString()


            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(this, "Please Enter the Details", Toast.LENGTH_SHORT).show()
            }
            else{
                loginUser(email, password)
            }
        }

    }
    private fun loginUser( email : String,  Password : String){
        auth.signInWithEmailAndPassword(email,Password).addOnSuccessListener {
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            Toast.makeText(this, "LogIn Successfull", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}