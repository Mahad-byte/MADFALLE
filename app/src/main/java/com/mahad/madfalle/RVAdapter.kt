package com.mahad.madfalle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahad.madfalle.RoomDatabase.StudentRecord

//Recycler View adapter(custom)
class RVAdapter(val context: Context,val userList:MutableList<StudentRecord>):RecyclerView.Adapter<RVAdapter.RVAdapterViewHolder> ()
{

    //View holder class, when this inner class calls it links with xml and we don't need to find everytime
    inner class RVAdapterViewHolder(val singleItem: View):RecyclerView.ViewHolder(singleItem)
    {
//        var userNameTV:TextView = singleItem.findViewById(R.id.si_user_name_tv)
//        var userEmailTV:TextView = singleItem.findViewById(R.id.si_user_email_tv)
        var id:TextView = singleItem.findViewById(R.id.id)
        var name:TextView = singleItem.findViewById(R.id.name)
        var email:TextView = singleItem.findViewById(R.id.email)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterViewHolder {
        return RVAdapterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.activity_recycler_room,parent,false) //kotlin representation of xml object
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: RVAdapterViewHolder, position: Int) {
        holder.id.text = userList[position].studentId.toString()
        holder.name.text = userList[position].studentName
        holder.email.text = userList[position].studentEmail
    }
}