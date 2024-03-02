package com.mahad.madfalle.Services

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mahad.madfalle.databinding.ActivityServiceBinding

class ServiceWorkingActivity : AppCompatActivity() {

    private lateinit var mServiceWorkingBinding:ActivityServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mServiceWorkingBinding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(mServiceWorkingBinding.root)
    }

    override fun onStart() {
        super.onStart()

        mServiceWorkingBinding.serviceStartServiceBtn.setOnClickListener {

            startService(Intent(this,MyService::class.java))
        }
        mServiceWorkingBinding.serviceStopServiceBtn.setOnClickListener {
            stopService(Intent(this,MyService::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}