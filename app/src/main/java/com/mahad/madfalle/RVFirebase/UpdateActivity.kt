package com.mahad.madfalle.RVFirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mahad.madfalle.AppConstants
import com.mahad.madfalle.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var mActivityUpdateBinding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityUpdateBinding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(mActivityUpdateBinding.root)
    }

    override fun onStart() {
        super.onStart()
        try {
            val firestore = Firebase.firestore
            val db = FirebaseFirestore.getInstance()

            val name = intent.getStringExtra("name")
            val email = intent.getStringExtra("email")
            val age = intent.getIntExtra("age", 0)
            mActivityUpdateBinding.nameUpdate.setText(name)
            mActivityUpdateBinding.emailUpdate.setText(email)
            mActivityUpdateBinding.ageUpdate.setText(age.toString())

            mActivityUpdateBinding.nameUpdate.requestFocus()

            val data = hashMapOf(
                "age" to age,
                "email" to email,
                "name" to name
                )
            val collectionAddress = firestore.collection(AppConstants.tempCollection)


            mActivityUpdateBinding.saveupdate.setOnClickListener {

                collectionAddress.document("1").update(
                    data as Map<String, Any?>
                ).addOnSuccessListener {
                    Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, RecyclerView::class.java))
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }catch (e: Exception){
            Toast.makeText(this, "Catch: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}