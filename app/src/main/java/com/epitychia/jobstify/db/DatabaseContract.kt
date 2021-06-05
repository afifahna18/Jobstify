package com.epitychia.jobstify.db

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    // Authority yang digunakan
    const val AUTHORITY = "com.epitychia.jobstify"
    const val SCHEME = "content"

    class JobColumns : BaseColumns {

        companion object{
            const val TABLE_NAME = "favorite_job"
            const val _ID = "_id"
            const val COLUMN_NAME_TITLE = "title"
            const val COLUMN_NAME_JOB_DES = "job_des"
            const val COLUMN_NAME_COMPANY = "company"
            const val COLUMN_NAME_LOCATION = "locaton"
            const val COLUMN_NAME_REQUIREMENT = "requirement"
            const val COLUMN_NAME_BENEFIT = "benefit"
            const val COLUMN_NAME_CATEGORY = "category"
            const val COLUMN_NAME_POSTER = "poster"

            // untuk membuat URI
            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }

    }
}