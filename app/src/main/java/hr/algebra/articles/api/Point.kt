package hr.algebra.articles.api

import com.google.android.gms.maps.model.LatLng

data class Point(
    val title: String,
    val address: String,
    val image: String,
    val lat: Double,
    val lng: Double
) {
    fun getLatLng() = LatLng(lat, lng)
    fun getInfo() = title + "\n" + address

}