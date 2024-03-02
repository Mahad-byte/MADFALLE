package com.mahad.madfalle

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
class RecylcerActivity : AppCompatActivity() {

    val userList = mutableListOf<RVModel>(
        RVModel("Ali Raza","dummy@gmail.com"), //calling RVModel Class to fill our list
        RVModel("Ali Raza","dummy@gmail.com"),
        RVModel("Ali Raza","dummy@gmail.com"),
        RVModel("Ali Raza","dummy@gmail.com"),
        RVModel("Ali Raza","dummy@gmail.com"),
        RVModel("Ali Raza","dummy@gmail.com"),
        RVModel("Ali Raza","dummy@gmail.com"),
        RVModel("Ali Raza","dummy@gmail.com"),
        RVModel("Ali Raza","dummy@gmail.com"),
        RVModel("Ali Raza","dummy@gmail.com"),
        RVModel("Ali Raza","dummy@gmail.com"),
        RVModel("Ali Raza","dummy@gmail.com")
    )
    //View Binding
    private lateinit var RecylcerActivity: ActivityRecyclerViewBinding
    private lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RecylcerActivity = ActivityRecyclerViewBinding.inflate(layoutInflater)

        setContentView(RecylcerActivity.root)
    }

    //creating adapter and populating recycler view with adapter
    override fun onStart() {
        super.onStart()
        //rvAdapter = RVAdapter(RecylcerActivity@this,userList)

        RecylcerActivity.rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        RecylcerActivity.rv.adapter = rvAdapter
    }
}