package com.example.mynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews.Fragments.DataFromNyt
import com.example.mynews.NewYorkTimesApi.NytWrapperSearch
import com.example.mynews.NewYorkTimesApi.mapNytDataToDataFromNytSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultSearchActivity : AppCompatActivity(), View.OnClickListener {

    //9) to Bind the Adapter to the RecyclerView and ResultSearcActivity Activity
    lateinit var datasFromNyt: MutableList<DataFromNyt>
    //11) Set the adapter
    lateinit var newsAdapter: ItemNewsAdapter

    lateinit var recyclerView: RecyclerView
    var topStoriesSciences = ArrayList<JSONParserTopStories.TopStoriesScience>()


    private val TAG = "Kiki"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_search)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_search_results_activity)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val call = App.nytServiceSearch.getNytDataFromSearch()
        call.enqueue(object : Callback<NytWrapperSearch> {


            override fun onResponse(call: Call<NytWrapperSearch>, response: Response<NytWrapperSearch>) {
                  Log.i(TAG, "NYT bulk Search response : ${response.body()}")
                response.body()?.let {

                    val dataFromNyt = mapNytDataToDataFromNytSearch(it)
                    Log.i(TAG, "DataFromSearchOnNyt response : $dataFromNyt") }

            }
            override fun onFailure(call: Call<NytWrapperSearch>, t: Throwable) {
                Log.e(TAG, "Nyt Search response : Could not load ! ", t)
                //  Toast.makeText(Context, "could not load Nyt Datas", Toast.LENGTH_SHORT).show()
            }

        })

        //10) Initialize the DatasfromNyt into the onCreateView
        //datasFromNyt = mutableListOf<DataFromNyt>()
//        // ICI trois test en local:
        datasFromNyt.add(
            DataFromNyt(
                "Il est libre Max!",
                "Monde ",
                " > France",
                "28 juin 2019",
                "https://www.nytimes.com/2019/03/06/us/politics/us-trade-deficit.html",
                "https://static01.nyt.com/images/2019/03/06/reader-center/06dc-deficit-hp/06dc-deficit-hp-thumbStandard.jpg",
                "The United States trade deficit in goods reached $891.3 billion in 2018 — the highest it’s ever been."
            )
        )
        datasFromNyt.add(
            DataFromNyt(
                "C'est sur",
                "europe",
                "brexit",
                "12 mars 2019",
                "https://www.nytimes.com/2019/03/06/business/bank-regulation.html",
                "https://static01.nyt.com/images/2019/03/07/business/06dc-crisis1/06dc-crisis1-thumbStandard.jpg",
                "The Federal Reserve said that it would no longer give banks a passing or failing grade on a portion of the annual stress tests used to ensure a bank had sufficient resources to lend during an economic downturn."
            )
        )
        datasFromNyt.add(
            DataFromNyt(
                "Il est CANAILLOUX!",
                "France ",
                " > Bearn",
                "28 juin 2019",
                "https://www.nytimes.com/2019/03/06/us/politics/us-trade-deficit.html",
                "https://static01.nyt.com/images/2019/03/06/reader-center/06dc-deficit-hp/06dc-deficit-hp-thumbStandard.jpg",
                "The United States trade deficit in goods reached $891.3 billion in 2018 — the highest it’s ever been."
            )

        )



//        //12) Initialize newsAdapter into the onCreate
        newsAdapter = ItemNewsAdapter(topStoriesSciences, this)


        //14) Collect the Recycler View from the Layout
        recyclerView = findViewById(R.id.result_activity_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)
        recyclerView.adapter = newsAdapter



    }
    override fun onClick(view: View) {
        Toast.makeText(this, "clické sur item + {${title}}", Toast.LENGTH_SHORT).show()
    }

}
