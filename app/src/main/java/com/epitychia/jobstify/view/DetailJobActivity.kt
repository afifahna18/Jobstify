package com.epitychia.jobstify.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.epitychia.jobstify.DetailJobViewModel
import com.epitychia.jobstify.R
import com.epitychia.jobstify.data.DataEntity
import com.epitychia.jobstify.databinding.ActivityDetailJobBinding
import com.epitychia.jobstify.db.DatabaseContract
import com.epitychia.jobstify.db.JobHelper
import kotlinx.android.synthetic.main.activity_detail_job.*

class DetailJobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailJobBinding

    companion object {
        const val EXTRA_DATA_JOB = "EXTRA_DATA_JOB"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailJobBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnApply.setOnClickListener{
            Toast.makeText(this@DetailJobActivity, "Congrats, Job Applied!" , Toast.LENGTH_SHORT).show()
        }
        val viewModel = ViewModelProvider(this@DetailJobActivity, ViewModelProvider.NewInstanceFactory())[DetailJobViewModel::class.java]

        //get data
//        val extras = intent.extras
//        if (extras != null) {
//            val jobId = extras.getString(EXTRA_DATA_JOB)
//            if (jobId != null) {
//
//                viewModel.setSelectedJob(jobId)
//
//                listJob(viewModel.getJob() as DataEntity)
//                for (data in DataDummy.generateDummyJob()) {
//                    if (data.title == jobId) {
//                        listJob(data)
//                    }
//                }
//            }
//        }
        //get data end

        val jobHelper = JobHelper.getInstance(applicationContext)
        jobHelper.open()

        val jobFav = intent.getParcelableExtra<DataEntity>(EXTRA_DATA_JOB)

        val checkResult = jobFav?.title?.let { jobHelper.queryById(it) }

        if (checkResult != null) {
            Log.d("Check Data", "${checkResult.count}")

            setStatusFavorite(checkResult.count > 0) // false

            binding.fabFav.setOnClickListener {
                jobHelper.open()

                val checkResult2 = jobFav.title?.let { jobHelper.queryById(it) }

                if (checkResult2 != null) {
                    if (checkResult2.count > 0) {
                        Log.d("clikced", "CLICKED FAB")
                        setUnFavorite(jobHelper, jobFav)
                    } else {
                        Log.d("clikced", "CLICKED FAB")
                        setFavorite(jobHelper, jobFav)
                    }
                }

                val checkResult3 = jobFav.title?.let { jobHelper.queryById(it) }

                if (checkResult3 != null) {
                    setStatusFavorite(checkResult3.count > 0)
                }
                jobHelper.close()
            }

        }

        jobHelper.close()



        if (jobFav != null) {


            binding.apply {
                jobFav?.apply {
                    tvTitle.text = title
                    tvCompany.text = company
                    tvLocation.text = location
                    tvJobDes.text = job_des
                    tvRequirement.text = requirement
                    tvBenefit.text = benefit

                    Glide.with(this@DetailJobActivity)
                        .load(poster)
                        .into(ivPoster)
                }



            }
        }



    }

    private fun listJob(dataEntity: DataEntity) {
        binding.tvTitle.text = dataEntity.title
        binding.tvCompany.text = dataEntity.company
        binding.tvLocation.text = dataEntity.location
        binding.tvJobDes.text = dataEntity.job_des
        binding.tvRequirement.text = dataEntity.requirement
        binding.tvBenefit.text = dataEntity.benefit

        Glide.with(this)
            .load(dataEntity.poster)
            .into(binding.ivPoster)

    }

    private fun setFavorite(jobHelper: JobHelper, job: DataEntity) {
        val values = ContentValues()
        values.put(DatabaseContract.JobColumns.COLUMN_NAME_TITLE, job.title)
        values.put(DatabaseContract.JobColumns.COLUMN_NAME_JOB_DES, job.job_des)
        values.put(DatabaseContract.JobColumns.COLUMN_NAME_COMPANY, job.company)
        values.put(DatabaseContract.JobColumns.COLUMN_NAME_LOCATION, job.location)
        values.put(DatabaseContract.JobColumns.COLUMN_NAME_REQUIREMENT, job.requirement)
        values.put(DatabaseContract.JobColumns.COLUMN_NAME_BENEFIT, job.benefit)
        values.put(DatabaseContract.JobColumns.COLUMN_NAME_CATEGORY, job.category)
        values.put(DatabaseContract.JobColumns.COLUMN_NAME_POSTER, job.poster)
        Log.d("db",job.toString())


        val result = jobHelper.insert(values)
        if (result < 0) {
            Toast.makeText(this, "Insert Failed $result", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Insert Success $result", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUnFavorite(jobHelper: JobHelper, job: DataEntity) {
        val result = jobHelper.deleteJob(job.title!!).toLong()
        if (result < 0) {
            Toast.makeText(this, "Delete Failed $result", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Delete Success $result", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFav.setImageResource(R.drawable.ic_favorite)
            Log.d("Status", "Sudah Di database")
        } else {
            binding.fabFav.setImageResource(R.drawable.ic_unfavorite)
            Log.d("Status", "Belum Di database")
        }
    }

}