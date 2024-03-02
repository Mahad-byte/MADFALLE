package com.mahad.madfalle

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.mahad.madfalle.databinding.ActivityAdapterBinding

class adapter : AppCompatActivity() {
    private lateinit var adapterbinding:ActivityAdapterBinding
    private var randomWordslist = mutableListOf("haseeb","mahad")
    private lateinit var randomwordsarrayadapter:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterbinding =ActivityAdapterBinding.inflate(layoutInflater)
        setContentView(adapterbinding.root)
    }
    override fun onStart() {
        super.onStart()
            randomwordsarrayadapter = ArrayAdapter(activity@this, R.layout.simple_list_item_1,randomWordslist)
            adapterbinding.ll1.adapter = randomwordsarrayadapter
    }
}