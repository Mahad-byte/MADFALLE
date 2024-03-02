package com.mahad.madfalle.servicesAssignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahad.madfalle.R
import com.mahad.madfalle.databinding.ActivitySrvactivityBinding

class SRVActivity : AppCompatActivity() {
    private lateinit var mSRVActivity: ActivitySrvactivityBinding
    private lateinit var Adapter: AudioAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSRVActivity =ActivitySrvactivityBinding.inflate(layoutInflater)
        setContentView(mSRVActivity.root)
    }

    override fun onStart() {
        super.onStart()
        // Extracting the name of audio file, if not it gives some integer value on audio name
        val audioName = resources.getResourceEntryName(R.raw.sample0)
        val audioName2 = resources.getResourceEntryName(R.raw.sample2)
        val audioName3 = resources.getResourceEntryName(R.raw.sample3)
        val audioName4 = resources.getResourceEntryName(R.raw.sample4)
        val audioName5 = resources.getResourceEntryName(R.raw.sample5)
        val audioMutableList = mutableListOf(
            ServiceModel(audioName),
            ServiceModel(audioName2),
            ServiceModel(audioName3),
            ServiceModel(audioName4),
            ServiceModel(audioName5)
        )
        Adapter = AudioAdapter(this, audioMutableList)
        mSRVActivity.srv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        mSRVActivity.srv.adapter = Adapter
    }
}