package com.mahad.madfalle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        email = findViewById(R.id.Email)
        pass = findViewById(R.id.Password)

        val btn_check: Button = findViewById(R.id.btn_navigate)

        val database_email = listOf(
            "L1F21BSCS0533@gmail.com",
            "L1F21BSCS1234@gmail.com",
            "L1F21BSCS0520@gmail.com",
            "L1F21BSCS0503@gmail.com",
            "L1F21BSCS1099@gmail.com"
        )
        val database_pass = listOf(
            "123",
            "1234",
            "12345",
            "123456",
            "1234567"
        )

        btn_check.setOnClickListener {
            try {
                if (database_email.contains(email.text.toString()) && database_pass.contains(pass.text.toString())) {
                    if(database_email.indexOf(email.text.toString()) == database_pass.indexOf(pass.text.toString())) {
                        startActivity(Intent(this, calculator::class.java))
                    }
                    else{
                        Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                else {
                    Toast.makeText(this, "No Email Found", Toast.LENGTH_SHORT)
                        .show()
                }
            }catch (e: Exception){
                Toast.makeText(this, "An Error Occurred", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
