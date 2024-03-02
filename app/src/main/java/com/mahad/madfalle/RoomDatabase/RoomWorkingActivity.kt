package com.mahad.madfalle.RoomDatabase

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.mahad.madfalle.RVAdapter
import com.mahad.madfalle.databinding.ActivityRoomWorkingBinding

class RoomWorkingActivity : AppCompatActivity() {

    private lateinit var mRoomWorkingBinding: ActivityRoomWorkingBinding
    private lateinit var mStudentDatabase: StudentDatabase
    private var idCounter = 0
    private var sharedPreferencesKey = "app_prefs"
    private lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRoomWorkingBinding = ActivityRoomWorkingBinding.inflate(layoutInflater)
        setContentView(mRoomWorkingBinding.root)
    }

    override fun onStart() {
        super.onStart()
        mStudentDatabase = Room.databaseBuilder(applicationContext,StudentDatabase::class.java,
            "StudentDb").allowMainThreadQueries().build()
        val sharedPreferences = getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
        idCounter = sharedPreferences.getInt("idCounter", 0)
        val studentDao= mStudentDatabase.getStudentDao()
        mRoomWorkingBinding.rAddRecordBtn.setOnClickListener {
            idCounter++
            val currentStudent = StudentRecord(idCounter,"Ali Raza","ali@gmail.com")
            val check= studentDao.insertSingleStudentRecord(currentStudent)
            if(check!=0L)
            {
                Toast.makeText(this,"Record inserted",Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(this,"Record not inserted",Toast.LENGTH_LONG).show()
            }
            sharedPreferences.edit().putInt("idCounter", idCounter).apply()
        }

        mRoomWorkingBinding.rGetRecordBtn.setOnClickListener {
            val studentData = studentDao.getAllStudentRecords()

            if(studentData.isNotEmpty())
            {
                var data =""
                val listOfStudents = studentData.toMutableList()
//                for(singleStudentRecord in studentData)
//                {
//                    data += "student id:${singleStudentRecord.studentId} \n" +
//                            "student name:${singleStudentRecord.studentName} \n" +
//                            "student email:${singleStudentRecord.studentEmail} \n"
//                }
                rvAdapter = RVAdapter(this,listOfStudents)

                mRoomWorkingBinding.rv.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false)
                mRoomWorkingBinding.rv.adapter = rvAdapter
            }
            else
            {
                Toast.makeText(this,"No Record Found",Toast.LENGTH_LONG).show()
            }
        }
    }
}