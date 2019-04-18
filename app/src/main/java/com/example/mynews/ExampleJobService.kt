package com.example.mynews

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.util.Log
import android.widget.Toast


class ExampleJobService : JobService() {


    val TAG: String = "ExampleJobService"
    var jobCancelled = false




    override fun onStartJob(params: JobParameters): Boolean {
        Log.d(TAG, "Job Started")
        Toast.makeText(this@ExampleJobService, "The job has started", Toast.LENGTH_SHORT).show()
        //JSONDownloaderNotification(this, newJsonForNotification ).execute()
        doBackgroundWork(params)
        return true
    }

    private fun doBackgroundWork(params: JobParameters) {
        Thread(object : Runnable {
            public override fun run() {
                for (i in 0..10) {
                   // Toast.makeText(this@ExampleJobService, "BackGroundWork on process", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Job Started on background")
                    if (jobCancelled) {
                        return
                    }
                    try {
                        Thread.sleep(1000)
                        //NOTIFICATION TO SHOW
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                    Log.d(TAG, "Job finished")
                    jobFinished(params, false)
                }
            }
        }).start()


    }
    // The job was not done, beacuse for example, the internet conneion was not on, so if we want to reschedulke, we have to return true, if not, false is ok
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "JOb Cancelled Before Completion")
        jobCancelled = true
        return true
    }
}


