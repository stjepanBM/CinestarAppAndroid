package hr.algebra.articles

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.articles.parser.parse
import hr.algebra.articles.utils.sendBroadcast

private const val JOB_ID = 1
class MoviesService :JobIntentService() { // this is also a context!!
    override fun onHandleWork(intent: Intent) {
        val articles = parse(this)
        val dao = (applicationContext as App).getMovieDao()
        dao.insert(articles)
        var articlesFromDB = dao.getMovies()
        println(articlesFromDB[0])
        sendBroadcast<MovieReceiver>()
    }

    companion object {
        fun enqueueWork(context: Context, intent: Intent) = enqueueWork(context, MoviesService::class.java, JOB_ID, intent)
    }
}