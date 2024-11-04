package com.muhasib.firebaselearning

import FirestoreAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.muhasib.firebaselearning.Person2
import com.muhasib.firebaselearning.R

class DataListFirestoreActivity : AppCompatActivity() {

    private lateinit var btnFirestore: Button
    private lateinit var recyclerViewFirestore: RecyclerView
    private lateinit var adapterFirestore: FirestoreAdapter
    private lateinit var people: MutableList<Person2>
    private var listenerRegistration: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_list_firestore)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        people = mutableListOf()
        btnFirestore = findViewById(R.id.btn_get_data_firestore)
        recyclerViewFirestore = findViewById(R.id.recyclerViewFirestore)

        recyclerViewFirestore.layoutManager = LinearLayoutManager(this)
        adapterFirestore = FirestoreAdapter(this, people) { position, documentId ->
            deleteItem(position, documentId)
        }
        recyclerViewFirestore.adapter = adapterFirestore

        btnFirestore.setOnClickListener {
            showDataToUI()
        }
    }

    private fun showDataToUI() {
        // Remove previous listener if exists
        listenerRegistration?.remove()

        // Add a new Firestore listener
        listenerRegistration = FirebaseFirestore.getInstance().collection("Vendor").addSnapshotListener { snapshot, error ->
            if (error != null) {
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            // Clear the list before adding new data
            people.clear()

            // Check for snapshot validity
            if (snapshot != null && !snapshot.isEmpty) {
                for (doc in snapshot.documents) {
                    val per = Person2()
                    per.name = doc.getString("Name") ?: "" // Using safe call and default value
                    per.age = doc.getString("Age") ?: "0"  // Using safe call and default value
                    per.documentId = doc.id
                    people.add(per)
                }
            }

            // Notify the adapter of data changes
            adapterFirestore.notifyDataSetChanged()
        }
    }

    private fun deleteItem(position: Int, documentId: String) {
        if (position < 0 || position >= people.size) {
            Log.e("DataListFirestoreActivity", "Invalid position for deletion: $position, List size: ${people.size}")
            return // Ensure the position is valid
        }

        FirebaseFirestore.getInstance().collection("Vendor").document(documentId).delete()
            .addOnSuccessListener {
                people.removeAt(position)
                adapterFirestore.notifyItemRemoved(position)
                Handler(Looper.getMainLooper()).postDelayed({
                    adapterFirestore.notifyItemRangeChanged(position, people.size)
                }, 2500)
                Log.d("DataListFirestoreActivity", "Item deleted successfully at position: $position")
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Unable to Delete Item: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("DataListFirestoreActivity", "Delete failed", e)
            }
    }

    override fun onStop() {
        super.onStop()
        // Remove Firestore listener when the activity is stopped
        listenerRegistration?.remove()
    }
}
