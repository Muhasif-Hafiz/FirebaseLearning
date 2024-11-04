import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhasib.firebaselearning.Person2
import com.muhasib.firebaselearning.R

class FirestoreAdapter(
    private val context: Context,
    private var people: MutableList<Person2>,
    private val onDeleteClick: (Int, String) -> Unit // Lambda for delete action
) : RecyclerView.Adapter<FirestoreAdapter.FViewHolder>() {

    class FViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.nameTextView)
        val itemAge: TextView = view.findViewById(R.id.ageTextView)
        val btnDelete: ImageButton = view.findViewById(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_row, parent, false)
        return FViewHolder(view)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: FViewHolder, position: Int) {
        val person = people[position]
        holder.itemName.text = person.name
        holder.itemAge.text = "Age: ${person.age}"

        holder.btnDelete.setOnClickListener {
            onDeleteClick(position, person.documentId) // Call the lambda for deletion
        }
    }

    // Optional: Method to update the list of people
    fun updateList(newPeople: List<Person2>) {
        people.clear()
        people.addAll(newPeople)
        notifyDataSetChanged()
    }
}
