package com.rud.fastjobs.viewmodel.jobDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rud.fastjobs.data.model.Job
import com.rud.fastjobs.data.model.User
import com.rud.fastjobs.data.repository.MyRepository

class JobDetailViewModel(private val myRepository: MyRepository, app: Application) : AndroidViewModel(app) {
    lateinit var currentUser: User
    lateinit var currentJob: Job

    fun getUserById(id: String, onSuccess: (User?) -> Unit = {}) {
        myRepository.getUserById(id, onSuccess = {
            currentUser = it!!
            onSuccess(it)
        })
    }

    fun getJobById(id: String, onSuccess: (Job?) -> Unit = {}) {
        myRepository.getJobById(id, onSuccess = {
            currentJob = it!!
            onSuccess(it)
        })
    }

    fun joinJob(jobId: String, onSuccess: () -> Unit = {}, onFailure: (Exception) -> Unit = {}) {
        myRepository.joinJob(currentUser, jobId, onSuccess, onFailure)
    }

    fun leaveJob(
        jobId: String,
        onSuccess: () -> Unit = {},
        onFailure: (Exception) -> Unit = {}
    ) {
        myRepository.leaveJob(currentUser.id!!, jobId, onSuccess, onFailure)
    }
}