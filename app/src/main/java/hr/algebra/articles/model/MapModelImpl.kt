package hr.algebra.articles.model

import android.util.Log
import hr.algebra.articles.MapContract
import hr.algebra.articles.api.Point
import retrofit2.Callback
import hr.algebra.articles.factory.getPointsApi
import retrofit2.Call
import retrofit2.Response

class MapModelImpl : MapContract.MapModel {
    override fun loadPoints(listener: MapContract.MapModel.OnPointsLoadedListener) {
        val pointsApi = getPointsApi()
        pointsApi.getPoints().enqueue(object: Callback<Map<String, Point>> {
            override fun onResponse(
                call: Call<Map<String, Point>>,
                response: Response<Map<String, Point>>
            ) {
                if (response.body() != null) {
                    listener.onPointsLoaded(response.body()!!.values)
                }
            }

            override fun onFailure(call: Call<Map<String, Point>>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }

        })

    }
}