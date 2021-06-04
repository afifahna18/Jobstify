package com.epitychia.jobstify.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataEntity(
    var id: Int = 0,
    var title: String? = null,
    var job_des: String? = null,
    var company: String? = null,
    var location: String? = null,
    var requirement: String? = null,
    var benefit: String? = null,
    var category: String? = null,
    var poster: Int = 0

): Parcelable