package com.epitychia.jobstify.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.epitychia.jobstify.data.DataEntity
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.COLUMN_NAME_BENEFIT
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.COLUMN_NAME_CATEGORY
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.COLUMN_NAME_COMPANY
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.COLUMN_NAME_JOB_DES
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.COLUMN_NAME_LOCATION
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.COLUMN_NAME_POSTER
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.COLUMN_NAME_REQUIREMENT
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.COLUMN_NAME_TITLE
import java.sql.SQLException
import java.util.ArrayList
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.TABLE_NAME
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion._ID



class JobHelper(context: Context) {

    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: JobHelper? = null

        fun getInstance(context: Context): JobHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: JobHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    /**
     * Ambil data dari semua note yang ada di dalam database
     * @return cursor hasil queryAll
     */
    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int{
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = $id", null)
    }

    fun getAllJob(): ArrayList<DataEntity> {
        val arrayList = ArrayList<DataEntity>()
        val cursor = database.query(
            DATABASE_TABLE, null, null, null, null, null,
            "$._ID ASC", null
        )
        cursor.moveToFirst()
        var job: DataEntity
        if (cursor.count > 0) {
            do {

                job = DataEntity()
                job.id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID))
                job.title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TITLE))
                job.job_des = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_JOB_DES))
                job.company = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_COMPANY))
                job.location = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_LOCATION))
                job.requirement = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_REQUIREMENT))
                job.benefit = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_BENEFIT))
                job.category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CATEGORY))
                job.poster = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_POSTER))


                arrayList.add(job)
                cursor.moveToNext()

            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$COLUMN_NAME_TITLE = ?",
            arrayOf(id),
            null,
            null,
            null,
            null,
        )
    }


    fun insertJob(job: DataEntity): Long{
        val args = ContentValues()
        args.put(COLUMN_NAME_TITLE, job.title)
        args.put(COLUMN_NAME_JOB_DES, job.job_des)
        args.put(COLUMN_NAME_COMPANY, job.company)
        args.put(COLUMN_NAME_LOCATION, job.location)
        args.put(COLUMN_NAME_REQUIREMENT, job.requirement)
        args.put(COLUMN_NAME_BENEFIT, job.benefit)
        args.put(COLUMN_NAME_CATEGORY, job.category)
        args.put(COLUMN_NAME_POSTER, job.poster)
        return database.insert(DATABASE_TABLE, null, args)
    }

    fun deleteJob(title: String): Int {
        return database.delete(TABLE_NAME, "$COLUMN_NAME_TITLE = '$title'", null)
    }


}