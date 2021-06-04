package com.epitychia.jobstify

import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import com.epitychia.jobstify.data.DataEntity
import com.epitychia.jobstify.utils.DataDummy

class JobCategoryViewModel : ViewModel() {

    fun getJobs() : List<DataEntity> = DataDummy.generateDummyJob()

    fun getJobsCategory(category: String) : List<DataEntity> {
        val data = mutableListOf<DataEntity>()
        for (i in getJobs().indices) {
            if (getJobs()[i].category == category) {
                data.add(getJobs()[i])
            }
        }
        return data
    }

}