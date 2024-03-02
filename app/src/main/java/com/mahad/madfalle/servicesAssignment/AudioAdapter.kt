package com.mahad.madfalle.servicesAssignment

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahad.madfalle.R

class AudioAdapter(val context: Context, val userList:MutableList<ServiceModel>): RecyclerView.Adapter<AudioAdapter.AudioAdapterViewHolder>() {
    inner class AudioAdapterViewHolder(val item: View): RecyclerView.ViewHolder(item){
        val Name: TextView = item.findViewById(R.id.Name)
        val play: Button = item.findViewById(R.id.start)
        val stop: Button = item.findViewById(R.id.stop)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioAdapterViewHolder {
        return AudioAdapterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.singel_item_service,parent,false) //kotlin representation of xml object
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: AudioAdapterViewHolder, position: Int) {
        holder.Name.text = userList[position].name

        holder.play.setOnClickListener {

            val context: Context = it.context
            val intent = Intent(context, Service::class.java)

            // Putting position in service to start the correct audio on each entry of recycler view
            /* This *position* refers to the position of an entry in recycler view, example first audio will be on 0 position of recycler view so on*/
            intent.putExtra("position", position)
            // Stopping the service if it is started before, for synchronization
            context.stopService(intent)
            context.startService(intent)
            Log.i("intent position", "$position")
        }
        holder.stop.setOnClickListener {
            val context: Context = it.context
            context.stopService(Intent(context, Service::class.java))
        }
    }
}