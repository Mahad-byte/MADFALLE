package com.mahad.madfalle.mid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahad.madfalle.R

//Recycler View adapter(custom)
class MidAdapter(val context: Context,val userList:MutableList<RVMidModel>):RecyclerView.Adapter<MidAdapter.RVAdapterViewHolderMid> ()
{

    //View holder class, when this inner class calls it links with xml and we don't need to find everytime
    inner class RVAdapterViewHolderMid(val singleItem: View):RecyclerView.ViewHolder(singleItem)
    {
        val title: TextView = singleItem.findViewById(R.id.title)
        val description: TextView = singleItem.findViewById(R.id.description)
        val priority: TextView = singleItem.findViewById(R.id.priority)
        val checkbox: CheckBox = singleItem.findViewById(R.id.checkbox)
        val date: TextView = singleItem.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterViewHolderMid {
        return RVAdapterViewHolderMid(
            LayoutInflater.from(context).inflate(
                R.layout.mid_sigle_item,
                parent,
                false
            ) //kotlin representation of xml object
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: RVAdapterViewHolderMid, position: Int) {
        holder.title.text = userList[position].title
        holder.description.text = userList[position].description
        holder.priority.text = userList[position].priority
        holder.checkbox.isChecked = userList[position].checkBox
        holder.date.text = userList[position].date
    }
}