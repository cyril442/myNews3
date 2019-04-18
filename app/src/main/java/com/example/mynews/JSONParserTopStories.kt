package com.example.mynews

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
class JSONParserTopStories(private var c: Context, private var jsonData: String) :
    AsyncTask<Void, Void, Boolean>() {

    override fun doInBackground(vararg params: Void?): Boolean {
        parseData()
        return true
    }


    private lateinit var pd: ProgressDialog
    private var topStoriesSciences = ArrayList<TopStoriesScience>()


    class TopStoriesScience(
        private var m_title: String,
        private var m_section: String,
        private var m_subsection: String,
        private var m_updated_date: String
//        private var m_url: String,
//        private var m_byline: String,
//        private var m_url_multimedia: String
    ) {

        companion object {

            fun  newInstance(): TopStoriesScience {
                return  newInstance()
            }
        }

        fun getTitle(): String? {
            return m_title
        }

        fun getSection(): String? {
            return m_section
        }

        fun getSubsection(): String? {
            return m_subsection
        }

        fun getUpdated_date(): String? {
            return m_updated_date
        }

        operator fun get(position: Int): Int {
            return position
        }

//        fun getUrl(): String? {
//            return m_url
//        }
//
//        fun getByline(): String? {
//            return m_byline
//        }
//
//        fun getUrlMultimedia(): String {
//            return m_url_multimedia
//        }
 //   }

//    class MrAdapter(private var c: Context, private var topStoriesSciences: ArrayList<TopStoriesScience>) :
//        RecyclerView.Adapter<MrAdapter.MyViewHolder>() {
//
//        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//
//        class MyViewHolder (val recyclerView: RecyclerView) : RecyclerView.ViewHolder(recyclerView) {}
//
//        // Create new views (invoked by the layout manager)
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//            // create a new view
//            val rv = LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_news, parent, false) as RecyclerView
//
//            // set the view's size, margins, paddings and layout parameters
//
//            return MyViewHolder(rv)
//        }
//
//        // Replace the contents of a view (invoked by the layout manager)
//        override fun getItemCount(): Int {
//           return topStoriesSciences.size
//        }
//
//         fun onBindViewHolder(holder: MyViewHolder, position: Int, view: RecyclerView, viewGroup: ViewGroup?, i :Int) {
//            var convertView = view
////            if (convertView == null) {
////                convertView = LayoutInflater.from(c).inflate(R.layout.item_news, viewGroup, false) as RecyclerView
////            }
//
//            val titleTxt = convertView.findViewById<TextView>(R.id.title) as TextView
//            val sectionTxt = convertView.findViewById<TextView>(R.id.section) as TextView
//            val subsectionTxt = convertView.findViewById<TextView>(R.id.subsection) as TextView
//            val updatedDateTxt = convertView.findViewById<TextView>(R.id.date) as TextView
////            val urlTxt = convertView.findViewById<TextView>(R.id.) as TextView
////            val bylineTxt = convertView.findViewById<TextView>(R.id.bylineTxt) as TextView
////            val urlMultimediaTxt = convertView.findViewById<TextView>(R.id.urlMultimediaTxt) as TextView
//
//
//            val topStoriesSciences = this.getItem(i) as TopStoriesScience
//
//            titleTxt.text = topStoriesSciences.getTitle()
//            sectionTxt.text = topStoriesSciences.getSection()
//            subsectionTxt.text = topStoriesSciences.getSubsection()
//            updatedDateTxt.text = topStoriesSciences.getUpdated_date()
////            urlTxt.text = topStoriesSciences.getUrl()
////            bylineTxt.text = topStoriesSciences.getByline()
////            urlMultimediaTxt.text = topStoriesSciences.getUrlMultimedia()
//
//
//            convertView.setOnClickListener {
//                Toast.makeText(c, topStoriesSciences.getTitle(), Toast.LENGTH_SHORT).show()
//            }
//
//            return convertView as Unit
//        }
//
//
//        fun getItem(pos: Int): Any {
//            return topStoriesSciences[pos]
//        }
//
//        override fun getItemId(pos: Int): Long {
//            return pos.toLong()
//        }

    //Inflate row_moadel and return it
//        override  fun getView(i: Int, view: View?, viewGroup: ViewGroup?): RecyclerView {
//            var convertView = view
//            if (convertView == null) {
//                convertView = LayoutInflater.from(c).inflate(R.layout.item_news, viewGroup, false) as RecyclerView
//            }
//
//            val titleTxt = convertView.findViewById<TextView>(R.id.title) as TextView
//            val sectionTxt = convertView.findViewById<TextView>(R.id.section) as TextView
//            val subsectionTxt = convertView.findViewById<TextView>(R.id.subsection) as TextView
//            val updatedDateTxt = convertView.findViewById<TextView>(R.id.date) as TextView
////            val urlTxt = convertView.findViewById<TextView>(R.id.) as TextView
////            val bylineTxt = convertView.findViewById<TextView>(R.id.bylineTxt) as TextView
////            val urlMultimediaTxt = convertView.findViewById<TextView>(R.id.urlMultimediaTxt) as TextView
//
//
//            val topStoriesSciences = this.getItem(i) as TopStoriesScience
//
//            titleTxt.text = topStoriesSciences.getTitle()
//            sectionTxt.text = topStoriesSciences.getSection()
//            subsectionTxt.text = topStoriesSciences.getSubsection()
//            updatedDateTxt.text = topStoriesSciences.getUpdated_date()
////            urlTxt.text = topStoriesSciences.getUrl()
////            bylineTxt.text = topStoriesSciences.getByline()
////            urlMultimediaTxt.text = topStoriesSciences.getUrlMultimedia()
//
//
//            convertView.setOnClickListener {
//                Toast.makeText(c, topStoriesSciences.getTitle(), Toast.LENGTH_SHORT).show()
//            }
//
//            return convertView as RecyclerView
//        }
}

// PARSE JSON DATA

 fun parseData(): ArrayList<String> {


    try {


        var jo: JSONObject


        topStoriesSciences.clear()
        var topStoriesScience: JSONParserTopStories.TopStoriesScience


        jo = JSONObject(jsonData)
        val ja = jo.getJSONArray("results")



        for (i in 0 until ja.length()) {
            jo = ja.getJSONObject(i)


            val title = jo.getString("title")
            val section = jo.getString("section")
            val subsection = jo.getString("subsection")
            val updated_date = jo.getString("updated_date")
//                val url = jo.getString("url")
//                val byline = jo.getString("byline")


//                val jam = jo.getJSONArray("multimedia")
//
//                for (i in 0 until jam.length()) {
//
//                    var jom = jam.getJSONObject(i)
//                    val url_multimedia = jom.getString("url")


            topStoriesScience =
                TopStoriesScience(
                    title,
                    section,
                    subsection,
                    updated_date
                )
            topStoriesSciences.add(topStoriesScience)

        }



        return topStoriesSciences as ArrayList<String>

    } catch (e: JSONException) {
        e.printStackTrace()
        return topStoriesSciences as ArrayList<String>

    }
}


//    override fun onPreExecute() {
//        super.onPreExecute()
//
//        pd = ProgressDialog(c)
//        pd.setTitle("Parse Json")
//        pd.setMessage("Parsing... Please Wait")
//        pd.show()
//    }
//
//    override fun doInBackground(vararg voids: Void): Boolean? {
//        return parse()
//    }
//
//    override fun onPostExecute(isParsed: Boolean?) {
//        super.onPostExecute(isParsed)
//
//        pd.dismiss()
//        if (isParsed!!) {
//            //BIND
//         //   val newsAdapter = ItemNewsAdapter.ViewHolder(topStoriesSciences)
//////             recyclerView  = recyclerView.findViewById(R.id.top_stories_recycler_view)
//////            val viewAdapter = MrAdapter(topStoriesSciences)
//////
//////            recyclerView.layoutManager = LinearLayoutManager(c)
//////            recyclerView.setHasFixedSize(false)
////            recyclerView.adapter = MrAdapter(c, topStoriesSciences) as RecyclerView.Adapter<RecyclerView.ViewHolder?>
////            //asRecyclerView.Adapter<*>?
//
//            viewManager = LinearLayoutManager(c)
//            viewAdapter = MrAdapter(c, topStoriesSciences)
//
//            recyclerView = recyclerView.findViewById<RecyclerView>(R.id.top_stories_recycler_view).apply{
//                setHasFixedSize(true)
//                layoutManager = viewManager
//               adapter = viewAdapter
//
//            }
//
//
//        } else {
//            Toast.makeText(
//                c,
//                "Unable to Parse that Data. Are you sure it is a valid Jsondata? Json Exception was raised ",
//                Toast.LENGTH_LONG
//            ).show()
//            Toast.makeText(c, "This the data we are trying to Parse: " + jsonData, Toast.LENGTH_LONG).show()
//
//        }
//    }
}
