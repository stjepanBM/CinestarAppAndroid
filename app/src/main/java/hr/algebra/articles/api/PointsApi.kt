package hr.algebra.articles.api

import retrofit2.Call
import retrofit2.http.GET

interface PointsApi {
    @GET("/points/.json")
    fun getPoints() : Call<Map<String, Point>>
}