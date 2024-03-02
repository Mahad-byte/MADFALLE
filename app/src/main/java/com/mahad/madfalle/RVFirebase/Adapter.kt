package com.mahad.madfalle.RVFirebase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mahad.madfalle.AppConstants
import com.mahad.madfalle.R

class Adapter(val context: Context, val userList:MutableList<Model>):RecyclerView.Adapter<Adapter.AdapterViewHolder>()
{
    inner class AdapterViewHolder(singelItem: View):RecyclerView.ViewHolder(singelItem)
    {
        var age: TextView = singelItem.findViewById(R.id.age)
        var name: TextView = singelItem.findViewById(R.id.name)
        var email: TextView = singelItem.findViewById(R.id.email)
        var edit: Button = singelItem.findViewById(R.id.edit)
        var delete: Button = singelItem.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.single_item_firebase,parent,false) //kotlin representation of xml object
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.age.text = userList[position].age.toString()
        holder.name.text = userList[position].name
        holder.email.text = userList[position].email
        val intent = Intent(context, UpdateActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        try {
            holder.edit.setOnClickListener {
                val context: Context = it.context
                intent.putExtra("name", holder.name.text.toString())
                intent.putExtra("email", holder.email.text.toString())
                intent.putExtra("age", holder.age.text.toString().toInt())
                context.startActivity(intent)
            }
        }
        catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        try {
            holder.delete.setOnClickListener {
                try {
                    val firestore = Firebase.firestore
                    val collectionAddress = firestore.collection(AppConstants.tempCollection)

                    val dataDelete = hashMapOf(
                        "name" to FieldValue.delete()
                    )

                    collectionAddress.document("1").update(
                        dataDelete as Map<String, Any?>
                    ).addOnSuccessListener {

                        Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
                        val display = FirebaseData().getDataFromFireBase()
                        //context.startActivity(Intent(context, RecyclerView::class.java))
                    }.addOnFailureListener {
                        Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show()
                    }
                }
                catch (e: Exception){
                    Toast.makeText(context, "Failed! In Catch", Toast.LENGTH_SHORT).show()
                }
            }
        }
        catch (e: Exception){
            Toast.makeText(context, "Failed on catch!", Toast.LENGTH_SHORT).show()
        }
    }
}

