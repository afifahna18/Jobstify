package com.epitychia.jobstify

import androidx.lifecycle.ViewModel
import com.epitychia.jobstify.data.DataEntity
import com.epitychia.jobstify.utils.DataDummy

class JobViewModel : ViewModel() {

    fun getJobs() : List<DataEntity> = DataDummy.generateDummyJob()
}