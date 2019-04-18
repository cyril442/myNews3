package com.example.mynews.NewYorkTimesApi

import com.google.gson.annotations.SerializedName

data class NytWrapper ( val results : ArrayList<ResultsData>,
                       val multimedia : ArrayList<Multimedia>)


data class ResultsData (
    val section : String,
    val subsection : String,
    val title : String,
    @SerializedName("url") val urlArticle : String,
    @SerializedName("updated_date") val date : String,
    @SerializedName ("multimedia") val multimedia : ArrayList<Multimedia>?
)


data class Multimedia (
    @SerializedName("url") val iconUrl : String,
    val caption : String

)

// FROM SEARCH

data class NytWrapperSearch (val docs : ArrayList<Docs>,
                             val images: ArrayList<Images>?)


  data class Docs (

      @SerializedName("section_name") val section : String,
      @SerializedName("subsectionName") val subsection: String,
      @SerializedName("snippet") val title : String,
      @SerializedName("web_url") val urlArticle : String,
      @SerializedName("multimedia") val images: ArrayList<Images>?,
      @SerializedName("pub_date") val date : String

      )

data class Images (

    @SerializedName("url") val iconUrl : String,
    val caption : String

)





