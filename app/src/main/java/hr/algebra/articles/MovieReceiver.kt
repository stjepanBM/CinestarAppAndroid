package hr.algebra.articles

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.articles.utils.setBooleanPreference
import hr.algebra.articles.utils.startActivity

class MovieReceiver : BroadcastReceiver() { // this guy is not a context

    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED, true) // data is in the db
        context.startActivity<HostActivity>()
    }
}