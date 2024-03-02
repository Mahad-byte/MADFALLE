package com.mahad.madfalle.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StudentRecord::class], version = 1)
abstract class StudentDatabase: RoomDatabase() {

    abstract fun getStudentDao():StudentDAO
}