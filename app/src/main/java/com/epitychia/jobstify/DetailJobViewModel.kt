package com.epitychia.jobstify

import androidx.lifecycle.ViewModel
import com.epitychia.jobstify.data.DataEntity
import com.epitychia.jobstify.utils.DataDummy

class DetailJobViewModel : ViewModel() {

    private lateinit var jobId: String

    fun setSelectedJob(jobId: String) {
        this.jobId = jobId
    }

    fun getJob(): DataEntity {
        lateinit var job: DataEntity
        val dataEntities = DataDummy.generateDummyJob()
        for (dataEntity in dataEntities) {
            if (dataEntity.title == jobId) {
                job = dataEntity
                break
            }
        }
        return  job
    }


}