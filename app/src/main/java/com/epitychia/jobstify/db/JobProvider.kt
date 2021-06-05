package com.epitychia.jobstify.db

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.epitychia.jobstify.db.DatabaseContract.AUTHORITY
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.CONTENT_URI
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.TABLE_NAME

class JobProvider : ContentProvider() {

    companion object {
        private const val JOB = 1
        private const val JOB_ID = 2
        private lateinit var jobHelper: JobHelper
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, JOB)

            sUriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", JOB_ID)
        }
    }

    override fun onCreate(): Boolean {
        jobHelper = JobHelper.getInstance(context as Context)
        jobHelper.open()
        return true
    }

    override fun query(
        uri: Uri, strings: Array<String>?, s: String?,
        strings1: Array<String>?, s1: String?
    ): Cursor? {
        return when (sUriMatcher.match(uri)){
            JOB -> jobHelper.queryAll()
            JOB_ID -> jobHelper.queryById(uri.lastPathSegment.toString())
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        val added: Long = when (JOB){
            sUriMatcher.match(uri) -> jobHelper.insert(contentValues)
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)

        return Uri.parse("$CONTENT_URI/$added")

    }

    override fun update(
        uri: Uri, contentValues: ContentValues?, s: String?,
        strings: Array<String>?
    ): Int {
        val updated: Int = when(JOB_ID){
            sUriMatcher.match(uri) -> jobHelper.update(uri.lastPathSegment.toString(),contentValues)
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)

        return updated
    }

    override fun delete(uri: Uri, s: String?, strings: Array<String>?): Int {
        val deleted: Int = when(JOB_ID){
            sUriMatcher.match(uri) -> jobHelper.deleteById(uri.lastPathSegment.toString())
            else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)

        return deleted
    }
}