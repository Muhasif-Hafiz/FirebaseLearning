package com.muhasib.firebaselearning

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataActivity : AppCompatActivity() {

    private lateinit var btnGetData: Button
    private lateinit var recyclerView: RecyclerView
    private val people = mutableListOf<Person>()
    private lateinit var adapter: PersonAdapter // Declare adapter as a class variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        btnGetData = findViewById(R.id.btn_get_data)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize adapter and set it to the RecyclerView
        adapter = PersonAdapter(people)
        recyclerView.adapter = adapter

        btnGetData.setOnClickListener {
            val databaseReference = FirebaseDatabase.getInstance().getReference().child("Vendor1")
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        people.clear()
                        for (childSnapshot in snapshot.children) {
                            val name = childSnapshot.child("Name").getValue(String::class.java)
                            val ageString = childSnapshot.child("Age").getValue(String::class.java)

                            // Convert ageString to Int
                            val age = ageString?.toIntOrNull() // Safe conversion

                            // Create Person object with the converted age
                            if (name != null && age != null && age >15) {
                                people.add(Person(name, ageString)) // Adjust as needed if you keep age as String
                                Log.d("DataActivity", "Loaded person: $name, Age: $age")
                            }
                        }
                        adapter.notifyDataSetChanged() // Notify the adapter after data change

                        Toast.makeText(this@DataActivity, "Data Loaded Successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@DataActivity, "No data available", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@DataActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
