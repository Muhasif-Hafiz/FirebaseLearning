package com.muhasib.firebaselearning

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreActivity : AppCompatActivity() {
    private lateinit  var et_name :EditText
    private lateinit var et_add :EditText
    private lateinit var btn_add_data :Button
    private lateinit var fireStore : FirebaseFirestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firestore)

        et_add=findViewById(R.id.et_firestore_age)
        et_name=findViewById(R.id.et_firestore_name)
        btn_add_data=findViewById(R.id.btn_firestore_add)
        btn_add_data.setOnClickListener {
            val map: HashMap<String, String> = HashMap()
            map["Name"] = et_name.text.toString()
            map["Age"] = et_add.text.toString()


            FirebaseFirestore.getInstance()
                .collection("Vendor")


                .add(map)
                .addOnCompleteListener {
                    Toast.makeText(this@FirestoreActivity, "INSERTED", Toast.LENGTH_SHORT).show()
                }
        }


    }
}