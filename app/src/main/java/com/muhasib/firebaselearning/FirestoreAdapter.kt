package com.muhasib.firebaselearning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.Person
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class FirestoreAdapter( private var people : List<Person2>) : RecyclerView.Adapter<FirestoreAdapter.FViewHolder>() {

    class FViewHolder(view : View): RecyclerView.ViewHolder(view){

        var itemName= view.findViewById<TextView>(R.id.nameTextView)
        var itemAge=view.findViewById<TextView>(R.id.ageTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.each_row, parent,false)
        return FViewHolder(view)
    }

    override fun getItemCount(): Int {
     return   people.size
    }

    override fun onBindViewHolder(holder: FViewHolder, position: Int) {

        val person =people[position]
        holder.itemName.text=person.name
        holder.itemAge.text="Age: ${person.age}"
    }
}