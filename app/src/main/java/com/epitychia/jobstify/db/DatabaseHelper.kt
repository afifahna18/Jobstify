package com.epitychia.jobstify.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.TABLE_NAME

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "dbjobapp"

        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_JOB = "CREATE TABLE $TABLE_NAME" +
                " (${DatabaseContract.JobColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${DatabaseContract.JobColumns.COLUMN_NAME_TITLE} TEXT," +
                " ${DatabaseContract.JobColumns.COLUMN_NAME_JOB_DES} TEXT," +
                " ${DatabaseContract.JobColumns.COLUMN_NAME_COMPANY} TEXT," +
                " ${DatabaseContract.JobColumns.COLUMN_NAME_LOCATION} TEXT," +
                " ${DatabaseContract.JobColumns.COLUMN_NAME_REQUIREMENT} TEXT," +
                " ${DatabaseContract.JobColumns.COLUMN_NAME_BENEFIT} TEXT," +
                " ${DatabaseContract.JobColumns.COLUMN_NAME_CATEGORY} TEXT," +
                " ${DatabaseContract.JobColumns.COLUMN_NAME_POSTER} TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_JOB)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}