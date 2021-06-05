package com.epitychia.jobstify.db

import android.database.Cursor
import com.epitychia.jobstify.data.DataEntity

object MappingHelper {

    fun mapCursorToArrayList(jobCursor: Cursor): ArrayList<DataEntity>{
        val jobList = ArrayList<DataEntity>()

        jobCursor.apply {
            while (moveToNext()){
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.JobColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_TITLE))
                val job_des = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_JOB_DES))
                val company = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_COMPANY))
                val location = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_LOCATION))
                val requirement = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_REQUIREMENT))
                val benefit = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_BENEFIT))
                val category = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_CATEGORY))
                val poster = getInt(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_POSTER))

                jobList.add(DataEntity(id, title, job_des, company, location, requirement, benefit, category, poster))
            }
        }

        return jobList
    }

    fun mapCursorToObject(jobCursor: Cursor): DataEntity{
        var job = DataEntity()
        jobCursor.apply {
            moveToFirst()
            val id = getInt(getColumnIndexOrThrow(DatabaseContract.JobColumns._ID))
            val title = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_TITLE))
            val job_des = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_JOB_DES))
            val company = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_COMPANY))
            val location = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_LOCATION))
            val requirement = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_REQUIREMENT))
            val benefit = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_BENEFIT))
            val category = getString(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_CATEGORY))
            val poster = getInt(getColumnIndexOrThrow(DatabaseContract.JobColumns.COLUMN_NAME_POSTER))
            job = DataEntity(id,title, job_des, company, location, requirement, benefit, category, poster)
        }
        return job
    }

}