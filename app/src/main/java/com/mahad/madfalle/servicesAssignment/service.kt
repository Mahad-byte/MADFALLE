package com.mahad.madfalle.servicesAssignment

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.mahad.madfalle.R

class Service: Service() {
    private lateinit var mediaPlayer: MediaPlayer
    private var audioList = listOf(R.raw.sample0, R.raw.sample2, R.raw.sample3, R.raw.sample4, R.raw.sample5)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // Getting the value of position from the previous activity
        val position = intent?.getIntExtra("position", 0)
        Log.i("position: ", "$position")
        mediaPlayer = MediaPlayer.create(this, audioList[position!!]) // !! means the value will never be null

        //if(mediaPlayer.isPlaying) {
            //stopSelf()
            //return START_NOT_STICKY
            //mediaPlayer.stop()
        //}
        //else
        //{
            mediaPlayer.start()
            //Toast.makeText(this, "started", Toast.LENGTH_SHORT).show()
            return START_STICKY
        //}
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        //Toast.makeText(this,"Destroyed",Toast.LENGTH_SHORT).show()
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
