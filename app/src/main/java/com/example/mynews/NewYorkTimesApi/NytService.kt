package com.example.mynews.NewYorkTimesApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "92Nbf4KeZSKhJXGm5QA3eTgNJjFW61gW"
// , @Query("yourkey") yourkey : String = API_KEY
// (@Path("section") section : String)

interface NytService {

    @GET("svc/topstories/v2/sports.json?api-key=92Nbf4KeZSKhJXGm5QA3eTgNJjFW61gW")
    fun getNytDataTopStories() : Call<NytWrapper>
}

interface NytServiceSearch{

    @GET("svc/search/v2/articlesearch.json?q=business&api-key=92Nbf4KeZSKhJXGm5QA3eTgNJjFW61gW")
    fun getNytDataFromSearch() : Call<NytWrapperSearch>

}