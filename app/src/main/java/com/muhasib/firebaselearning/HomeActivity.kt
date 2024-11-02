package com.muhasib.firebaselearning

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import java.util.Objects

class HomeActivity : AppCompatActivity() {
    private lateinit var et_Add_name : EditText
    private lateinit var et_Add_Age : EditText
    private lateinit var btn_add : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

       var  textViewNextActivity :TextView=findViewById(R.id.NextPage)
        et_Add_name=findViewById(R.id.et_add_name)
        et_Add_Age=findViewById(R.id.et_Add_age)
        btn_add=findViewById(R.id.btn_Add_home)
        btn_add.setOnClickListener {

            var m : HashMap<String,String> = HashMap()
            m["Name"] = et_Add_name.text.toString()
            m["Age"] = et_Add_Age.text.toString()

            FirebaseDatabase.getInstance().getReference().child("Vendor1").push().setValue(m).addOnSuccessListener {
                Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                et_Add_name.text.clear()
                et_Add_Age.text.clear()
            }
        }
        textViewNextActivity.setOnClickListener {
            var intent = Intent(this, DataActivity::class.java)
            startActivity(intent)
        }
    }
}