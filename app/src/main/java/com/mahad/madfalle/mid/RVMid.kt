package com.mahad.madfalle.mid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahad.madfalle.databinding.ActivityRecyclerViewBinding

/*
Step 1 : Create the model class
Step 2 : Create layout resource file for single_item
Step 3 : Create objects fo the model class and add those to the list
Step 4 : Create Recycler Adapter for the Model Class
     4.1 - Create user define class for recyclerview adapter
     4.2 - Create inner class of adapter view holder inside the class created in step 4.1
*/

/*
  Make a Recycler Activity
  Populate the list with variables of Email and user that we created in RVModel
  */
class RecyclerMid : AppCompatActivity() {

    //View Binding
    private lateinit var RecyclerMid: ActivityRecyclerViewBinding
    private lateinit var rvAdapter:MidAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RecyclerMid = ActivityRecyclerViewBinding.inflate(layoutInflater)

        setContentView(RecyclerMid.root)
    }
    val userList_mid = mutableListOf<RVMidModel>(
        RVMidModel("Task Title","Task Description comes here and can be on two lines",
            "Priority: High", true, "2023-11-17"),
        RVMidModel("Task Title","Task Description comes here and can be on two lines",
            "Priority: High", true, "2023-11-17"),
        RVMidModel("Task Title","Task Description comes here and can be on two lines",
            "Priority: High", true, "2023-11-17"),
        RVMidModel("Task Title","Task Description comes here and can be on two lines",
            "Priority: High", true, "2023-11-17"),
        RVMidModel("Task Title","Task Description comes here and can be on two lines",
            "Priority: High", true, "2023-11-17"),
        RVMidModel("Task Title","Task Description comes here and can be on two lines",
            "Priority: High", true, "2023-11-17"),
        RVMidModel("Task Title","Task Description comes here and can be on two lines",
            "Priority: High", true, "2023-11-17"),
        RVMidModel("Task Title","Task Description comes here and can be on two lines",
            "Priority: High", true, "2023-11-17"),
        RVMidModel("Task Title","Task Description comes here and can be on two lines",
            "Priority: High", true, "2023-11-17"),
        //calling RVModel Class to fill our list

    )
    //creating adapter and populating recycler view with adapter
    override fun onStart() {
        super.onStart()
        rvAdapter = MidAdapter(this,userList_mid)

        RecyclerMid.rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        RecyclerMid.rv.adapter = rvAdapter
    }
}