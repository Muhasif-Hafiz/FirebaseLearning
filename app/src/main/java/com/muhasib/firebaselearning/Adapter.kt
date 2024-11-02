package com.muhasib.firebaselearning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhasib.firebaselearning.PersonAdapter.*


class PersonAdapter(private val people : List<Person>) :
    RecyclerView.Adapter<PersonAdapter.viewHolder>() {

    class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val nameTextView : TextView= itemView.findViewById(R.id.nameTextView)
        val ageTextView : TextView= itemView.findViewById(R.id.ageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
       val view =LayoutInflater.from(parent.context).inflate(R.layout.each_row, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
       return people.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       val person=people[position]
        holder.nameTextView.text=person.name
        holder.ageTextView.text="Age: ${person.age}"

    }
}