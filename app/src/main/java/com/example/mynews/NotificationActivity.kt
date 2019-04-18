package com.example.mynews

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.mynews.Fragments_Notification.JobSchedulerFragment
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_check_box.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class NotificationActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener,
    JobSchedulerFragment.onJobSchedulerFragmentSelected {


    // NOTIFICTATION FOR TESTTING
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelID = "com.example.mynews"
    private val description = "test de Notification"

    internal lateinit var btnSwitch: Switch
    val JOB_ID = 123
    val TAG = "Notification Activity"

    lateinit var artsNot : String
    lateinit var politicsNot : String
    lateinit var businessNot : String
    lateinit var sportsNot : String
    lateinit var entrepreneursNot : String
    lateinit var travelNot : String
    lateinit var newJsonForNotification : String




    override fun onClick(v: View?) {
        Toast.makeText(this, "onClick has been clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        Toast.makeText(this, "onCheckChanged", Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_notification_activity)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        btnSwitch = findViewById(R.id.btnSwitch)

        btnSwitch.setOnClickListener {

            if (btnSwitch.isChecked) {
                Toast.makeText(this, "Switch Button is checked", Toast.LENGTH_SHORT).show()
              // datasForJsonNotification()
                scheduleJob()

                //
            } else {
                Toast.makeText(this, "Switch Button is Unchecked", Toast.LENGTH_SHORT).show()
                cancelJob()
                //
            }

        }


    }



    override fun onJobSchedulerFragmentSelected(jobSchedulerFragment: JobSchedulerFragment) {
        Toast.makeText(this, "Hey, you selected !", Toast.LENGTH_SHORT).show()
    }

    fun scheduleJob() {
        val componentName = ComponentName(this, ExampleJobService::class.java)
        val info = JobInfo.Builder(JOB_ID, componentName)
            //   .setRequiresCharging(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)
            .setPeriodic(15 * 60 * 1000)
            .build()

        val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val resultCode = scheduler.schedule(info)
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled")
        } else {
            Log.d(TAG, "Job scheduling failed")
        }
    }


    fun cancelJob() {
        val scheduler = getSystemService(AppCompatActivity.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(JOB_ID)
        Log.d(TAG, "Job cancelled")
    }


     fun datasForJsonNotification() : String {

        val key : String = "92Nbf4KeZSKhJXGm5QA3eTgNJjFW61gW"

        var editText = findViewById<EditText>(R.id.edit_query)

        var queryNotification = editText.text
        var artNotification = checkBoxArts.isChecked
        var politicsNotification = checkBoxPolitics.isChecked
        var businessNotification = checkBoxBusiness.isChecked
        var sportNotification = checkBoxSport.isChecked
        var entrepreneursNotification = checkBoxEntrepreneurs.isChecked
        var travelNotification = checkBoxTravel.isChecked

        Toast.makeText(this, " editText : $queryNotification, $artNotification, $politicsNotification, $businessNotification, $sportNotification, $entrepreneursNotification, $travelNotification", Toast.LENGTH_LONG).show()


        when (artNotification) {
            true -> artsNot = "&fq=Arts"
            false -> artsNot = "" }

        when(politicsNotification) {
            true -> politicsNot = "&fq=Politics"
            false -> politicsNot = ""}

        when (businessNotification) {
            true -> businessNot = "&fq=Business"
            false -> businessNot = "" }

        when (sportNotification) {
            true -> sportsNot = "&fq=Sports"
            false -> sportsNot = "" }

        when (entrepreneursNotification) {
            true -> entrepreneursNot = "&fq=Entrepreneurs"
            false -> entrepreneursNot = "" }

        when (travelNotification) {
            true -> travelNot = "&fq=Travel"
            false -> travelNot = "" }



        // DATE BEGIN AND ENDING
        // ---- TODAY ----
        var calendar = Calendar.getInstance()

        var input = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime())


        var sdfIn = SimpleDateFormat("dd/MM/yyyy")
        var sdfOut = SimpleDateFormat("yyyyMMdd")

        var date = sdfIn.parse(input)

        var dateEndNotification = sdfOut.format(date)

        // DATE BEGIN AND ENDING
        // ---- YESTERDAY ----

        val mydate = Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24)
        val dateFormat = SimpleDateFormat("yyyyMMdd")
        val dateBeginNotification = dateFormat.format(mydate)


        // String with checked element
        val fq = "$artsNot$politicsNot$businessNot$sportsNot$entrepreneursNot$travelNot".trim()

        val jsonForNotification = "https://api.nytimes.com/svc/search/v2/articlesearch.json?begin_date=$dateBeginNotification&end_date=$dateEndNotification&facet_fields=news_desk&facet_filter=true$fq&q=$queryNotification&sort=relevance&api-key=$key"

        Log.d(TAG, "$jsonForNotification")
        Log.d(TAG, "$key")
        Log.d(TAG, "$fq")
        Log.d(TAG, " date yesterday : $dateBeginNotification")
        Log.d(TAG, "calendar $calendar")
        Log.d(TAG, " date today :$dateEndNotification")



         return jsonForNotification
    }
//
//    fun getJSONForNotification () : String {
//        var newJsonForNotification: String = datasForJsonNotification()
//        Log.d(TAG, " date today :$newJsonForNotification")
//     return newJsonForNotification
//    }
}
