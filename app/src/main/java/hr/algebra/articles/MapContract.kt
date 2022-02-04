package hr.algebra.articles

import hr.algebra.articles.api.Point
import hr.algebra.articles.api.PointsApi

interface MapContract {
    interface MapModel {
        interface OnPointsLoadedListener {
            fun onPointsLoaded(points: Collection<Point>)
        }
        fun loadPoints(listener: OnPointsLoadedListener)

    }
    interface MapView {
        fun addPoints(points: Collection<Point>)
    }
    interface MapPresenter {
        fun requestToLoadPoints()
        fun onDestroy()
    }
}