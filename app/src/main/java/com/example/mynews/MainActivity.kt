package com.example.mynews

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.mynews.Fragments.DataFromNyt
import com.example.mynews.Fragments.Tab1Fragment
import com.example.mynews.Fragments.Tab2Fragment
import com.example.mynews.Fragments.Tab3Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {



    // NOTIFICTATION FOR TESTTING
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelID = "com.example.mynews"
    private val description = "test de Notification"


//        //9) to Bind the Adapter to the RecyclerView and Main Activity
//        lateinit var topStoriesScience : MutableList<DataFromNyt>
//        //11) Set the adapter
//        lateinit var newsAdapter : ItemNewsAdapter

        // 5) Setting Up of the value pager Adapter
    var pagerAdapter : CustompagerAdapter? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 6) setting Up of the page Adapter in the Oncreate

        pagerAdapter = CustompagerAdapter(supportFragmentManager)
        pagerAdapter!!.addFragments(Tab1Fragment(), "Top Stories")
        pagerAdapter!!.addFragments(Tab2Fragment(), "Most Popular")
        pagerAdapter!!.addFragments(Tab3Fragment(), "Sport")


        //7) Adding pagerAdapter to viewpager
        customViewPager.adapter = pagerAdapter

        //8) Setting up viewpager with tabLayout
        customTabLayout.setupWithViewPager(customViewPager)


        // 1) Set the Custom Toolbar to the MainActivity
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


    }


    //2) Creation of the Menu associated to the toolbar when onCreate Activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main, menu)
        return true
    }

    //3) Management of the clicks on the item of the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Each case is treated
        when (item.itemId) {
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_notification -> {
                Toast.makeText(this, "Notification Button Clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_about -> {
                notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                Toast.makeText(this, "About Button Clicked", Toast.LENGTH_SHORT).show()
                laNotification()
                return true
            }
            R.id.action_help -> {
                Toast.makeText(this, "help Button Clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            // In else, we return the default value
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun laNotification() {

        val intent = Intent(this, LauncherActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelID, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelID)
                .setContentTitle("Content Title")
                .setContentText("test Notification")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)

        } else {
            builder = Notification.Builder(this)
                .setContentTitle("Content Title")
                .setContentText("test Notification")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())


    }
//    // 13) Implementation of the OnClick on the item
//    override fun onClick(view: View) {
//        if( view.tag != null){
//            Log.i("MainActivityClick", "Click sur un élément de la liste")
//            val intent = Intent(this, SearchActivity::class.java)
//            startActivity(intent)
//
//
//
//        }
//    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
