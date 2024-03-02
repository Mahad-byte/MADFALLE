package com.mahad.madfalle.RVFirebase

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mahad.madfalle.AppConstants

class FirebaseData {
    fun getDataFromFireBase(): MutableList<Model>{
        val firestore = Firebase.firestore
        val userList = mutableListOf<Model>()
        try {
            val collectionAddress = firestore.collection(AppConstants.tempCollection)
            collectionAddress.get().addOnSuccessListener {
                if(!(it.isEmpty)) {
                    for(record in it){
                        val name = record.data.get("name")
                        val email = record.data.get("email")
                        val age = record.data.get("age")
                        //getting single value in loop and updating list to be printed in recycler view
                        val model = Model(name.toString(), email.toString(), age.toString().toInt())
                        userList.add(model)
                    }
                }
            }.addOnFailureListener {
                Log.i("Failure on Firebase","${it.message}}")
            }
        }
        catch (e: Exception){
            Log.i("Failure on Firebase(Catch)","${e.message}}")
        }
        return userList
    }
}