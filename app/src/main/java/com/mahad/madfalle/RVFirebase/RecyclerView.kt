package com.mahad.madfalle.RVFirebase

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahad.madfalle.databinding.ActivityRecyclerView2Binding

class RecyclerView : AppCompatActivity() {
    private lateinit var mActivityRecyclerViewBinding: ActivityRecyclerView2Binding
    val display = FirebaseData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityRecyclerViewBinding = ActivityRecyclerView2Binding.inflate(layoutInflater)
        setContentView(mActivityRecyclerViewBinding.root)
    }

    override fun onStart() {
        super.onStart()
        mActivityRecyclerViewBinding.rv.layoutManager =
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mActivityRecyclerViewBinding.rv.adapter = Adapter(applicationContext, display.getDataFromFireBase())

        mActivityRecyclerViewBinding.fsSaveRecordBtn.setOnClickListener {
            saveRecordInFirestore()
        }
        mActivityRecyclerViewBinding.fsGetRecordBtn.setOnClickListener {
            try {
                mActivityRecyclerViewBinding.rv.adapter = Adapter(applicationContext, display.getDataFromFireBase())
            }
            catch (e : Exception){
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveRecordInFirestore() {
        try {
            if(mActivityRecyclerViewBinding.fsNameEt.text.isNotEmpty() and
                mActivityRecyclerViewBinding.fsEmailEt.text.isNotEmpty() and
                mActivityRecyclerViewBinding.fsAgeEt.text.isNotEmpty())
            {
                mActivityRecyclerViewBinding.fsProgressBar.visibility = View.VISIBLE

                val userName = mActivityRecyclerViewBinding.fsNameEt.text.toString()
                val userEmail = mActivityRecyclerViewBinding.fsEmailEt.text.toString()
                val userAge = mActivityRecyclerViewBinding.fsAgeEt.text.toString().toInt()

                val data = Model(
                    userName,
                    userEmail,
                    userAge
                )
            }
        }
        catch (_: Exception){

        }
    }
}

