package com.mahad.madfalle.Fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.mahad.madfalle.R

class DynamicFragmentActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_fragment)

        if(savedInstanceState==null)
        {
            findViewById<FragmentContainerView>(R.id.fragment_container).let {
                val parentFragment = ParentFragment()
                supportFragmentManager.beginTransaction().add(it.id,parentFragment).commit()
            }
        }
    }
    override fun sendData(data: String) {
        findViewById<FragmentContainerView>(R.id.fragment_container).let {
            val childFragment = ChildFragment.newInstance(data)
            supportFragmentManager.beginTransaction().replace(it.id,childFragment)
                .addToBackStack(null).commit()
        }
    }
}