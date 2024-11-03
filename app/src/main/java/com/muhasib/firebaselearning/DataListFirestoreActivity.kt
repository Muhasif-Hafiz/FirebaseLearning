package com.muhasib.firebaselearning

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class DataListFirestoreActivity : AppCompatActivity() {

    private  lateinit var btn_firestore : Button
    private lateinit var recyclerviewFirestore : RecyclerView
    private lateinit var adapterFirestore : FirestoreAdapter
    private lateinit var people  : MutableList<Person2>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_list_firestore)
        people= mutableListOf()
        btn_firestore=findViewById(R.id.btn_get_data_firestore)
        recyclerviewFirestore=findViewById(R.id.recyclerViewFirestore)
        recyclerviewFirestore.layoutManager=LinearLayoutManager(this)
        adapterFirestore= FirestoreAdapter(people)

        recyclerviewFirestore.adapter=adapterFirestore
        btn_firestore.setOnClickListener {

            showDataToUI()
        }

    }
    private fun showDataToUI(){
        FirebaseFirestore.getInstance().collection("Vendor").addSnapshotListener { snapshot, error ->

            if(error!=null){
             Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            people.clear()

            if(snapshot!=null && !snapshot.isEmpty){

                for(docs  in snapshot.documents){

                   val per = Person2()
                   per.name=docs.getString("Name")?:""
                    per.age=docs.getString("Age") ?: "0"
                    people.add(per)

                }
            }
            adapterFirestore.notifyDataSetChanged()

        }
    }
}