package com.mahad.madfalle.Firebase

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mahad.madfalle.AppConstants
import com.mahad.madfalle.CustomObjects
import com.mahad.madfalle.databinding.ActivityFireStoreBinding

class FireStoreActivity : AppCompatActivity() {

    private lateinit var mActivityFireStoreBinding: ActivityFireStoreBinding

    private val mFireStore = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityFireStoreBinding = ActivityFireStoreBinding.inflate(layoutInflater)
        setContentView(mActivityFireStoreBinding.root)
    }

    override fun onStart() {
        super.onStart()

        mActivityFireStoreBinding.fsSaveRecordBtn.setOnClickListener {
            saveRecord()
        }
        mActivityFireStoreBinding.fsGetRecordBtn.setOnClickListener {
            getRecords()
        }
    }

    private fun getRecords() {
        try
        {
            mActivityFireStoreBinding.fsProgressBar.visibility = View.VISIBLE
            val collectionAddress = mFireStore.collection(AppConstants.tempCollection)

            var retrievedData = ""
            collectionAddress.get().addOnSuccessListener {
                mActivityFireStoreBinding.fsProgressBar.visibility = View.INVISIBLE
                for (singleRecord in it)
                {
                    retrievedData += "name : ${singleRecord.data.get("name")} \n" +
                            "email: ${singleRecord.data.get("email")} \n" +
                            "age: ${singleRecord.data.get("age")} \n"
                }

                mActivityFireStoreBinding.fsRecordTv.text = retrievedData
            }.addOnFailureListener {
                mActivityFireStoreBinding.fsProgressBar.visibility = View.INVISIBLE
                mActivityFireStoreBinding.fsRecordTv.text = "Failed to get data because of ${it.message}"
            }
        }
        catch (ex:Exception)
        {
            mActivityFireStoreBinding.fsProgressBar.visibility = View.INVISIBLE
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_LONG).show()
        }
    }

    private fun saveRecord() {
        try {
            if(mActivityFireStoreBinding.fsNameEt.text.isNotEmpty() and
                mActivityFireStoreBinding.fsEmailEt.text.isNotEmpty() and
                mActivityFireStoreBinding.fsAgeEt.text.isNotEmpty())
            {
                mActivityFireStoreBinding.fsProgressBar.visibility = View.VISIBLE

                val userName = mActivityFireStoreBinding.fsNameEt.text.toString()
                val userEmail = mActivityFireStoreBinding.fsEmailEt.text.toString()
                val userAge = mActivityFireStoreBinding.fsAgeEt.text.toString().toInt()

                //custom object for data
                val data = CustomObjects(
                    userName,
                    userEmail,
                    userAge
                )

                val collectionAddress = mFireStore.collection(AppConstants.tempCollection)
                collectionAddress.add(data).addOnSuccessListener {
                    mActivityFireStoreBinding.fsProgressBar.visibility  = View.VISIBLE
                    Toast.makeText(applicationContext,"Document is added with id:${it.id}",Toast.LENGTH_SHORT)
                        .show()

                    mActivityFireStoreBinding.fsNameEt.text.clear()
                    mActivityFireStoreBinding.fsEmailEt.text.clear()
                    mActivityFireStoreBinding.fsAgeEt.text.clear()

                    mActivityFireStoreBinding.fsNameEt.requestFocus()
                    mActivityFireStoreBinding.fsProgressBar.visibility = View.INVISIBLE

                }.addOnFailureListener {
                    mActivityFireStoreBinding.fsProgressBar.visibility = View.INVISIBLE
                    mActivityFireStoreBinding.fsRecordTv.text = "Failed: ${it.message}"
                }
            }
            else if(mActivityFireStoreBinding.fsEmailEt.text.isEmpty()){
                Toast.makeText(applicationContext,"Please enter the name",Toast.LENGTH_LONG).show()
                mActivityFireStoreBinding.fsNameEt.requestFocus()
            }
            else if(mActivityFireStoreBinding.fsEmailEt.text.isEmpty())
            {
                Toast.makeText(applicationContext,"Please enter the email",Toast.LENGTH_LONG).show()
                mActivityFireStoreBinding.fsEmailEt.requestFocus()
            }
            else if(mActivityFireStoreBinding.fsAgeEt.text.isEmpty())
            {
                Toast.makeText(applicationContext,"Please enter the age",Toast.LENGTH_LONG).show()
                mActivityFireStoreBinding.fsAgeEt.requestFocus()
            }
        }
        catch (ex: Exception){
            mActivityFireStoreBinding.fsProgressBar.visibility = View.INVISIBLE
            Toast.makeText(applicationContext,ex.message,Toast.LENGTH_LONG).show()
        }
    }
}