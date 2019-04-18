package com.example.mynews

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.example.mynews.Fragments_Search.CalendarFragment
import com.example.mynews.Fragments_Search.CheckBoxFragment
import kotlinx.android.synthetic.main.fragment_check_box.*

class SearchActivity : AppCompatActivity(), View.OnClickListener, CalendarFragment.CalendarFragmentListener {


    lateinit var calendarFragment : CalendarFragment


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_search_activity)
        setSupportActionBar(toolbar)
        supportActionBar!!. setDisplayHomeAsUpEnabled(true)

        // Recuperation of the Fragment into the Search Activity
        calendarFragment = CalendarFragment()



        // the FindViewById from the Fragments collected into the Search Activity for Treatment before request HTTP to the server
        val editTextForSearch = findViewById<EditText>(R.id.edit_query)
        val button = findViewById<Button>(R.id.button_search)

        // Begin Date
        // End date


        var checkedArt : String
        var checkedPolitics : String
        var checkedBusiness : String
        var checkedSport  : String
        var checkedEntrepreneurs  : String
        var checkedTravel  : String




        // OnClick of the button Search
        button.setOnClickListener(View.OnClickListener {
            val querytext = editTextForSearch.text
        //    var begin = calendarFragment.entryDate
         //   var end = calendarFragment.endDate

            var artsChecked : Boolean = checkBoxArts.isChecked
            if (artsChecked == true) { checkedArt = "Arts" } else {checkedArt = ""}

            val politicsChecked : Boolean = checkBoxPolitics.isChecked
            if (politicsChecked == true) { checkedPolitics = "Politics" } else {checkedPolitics = ""}

            val businessChecked : Boolean = checkBoxBusiness.isChecked
            if (businessChecked == true) { checkedBusiness = "Business" } else {checkedBusiness = ""}

            val sportChecked : Boolean = checkBoxSport.isChecked
            if (sportChecked == true) { checkedSport = "Sport" } else {checkedSport = ""}

            val entrepreneursChecked : Boolean = checkBoxEntrepreneurs.isChecked
            if (entrepreneursChecked == true) { checkedEntrepreneurs = "Entrepreneurs" } else {checkedEntrepreneurs = ""}

            val travelChecked : Boolean = checkBoxTravel.isChecked
            if (travelChecked == true) { checkedTravel = "Travel" } else {checkedTravel = ""}





            Toast.makeText(this, "Search Button Clicked", Toast.LENGTH_SHORT).show()
            Log.i("Texte affiché ", " $querytext, $checkedArt, $checkedPolitics, $checkedBusiness, $checkedSport, $checkedEntrepreneurs, $checkedTravel")

       //     Log.i("BEG", " $begin ")

            val intent = Intent(this, ResultSearchActivity::class.java )
            startActivity(intent)

        })

    }
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onDateBegin(entryDate: String) {

     }

}
