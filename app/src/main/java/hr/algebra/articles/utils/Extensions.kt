package hr.algebra.articles.utils

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.preference.PreferenceManager

// we can apply any animation on any view
fun View.applyAnimation(resourceId: Int) = startAnimation(AnimationUtils.loadAnimation(context, resourceId))

inline fun<reified T: Activity> Context.startActivity() = startActivity(Intent(this,T::class.java).apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // very important for the new versions of Android
})

inline fun<reified T: Activity> Context.startActivity(key: String, value: Int) = startActivity(Intent(this,T::class.java).apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // very important for the new versions of Android
    putExtra(key, value)
})

inline fun<reified T: BroadcastReceiver> Context.sendBroadcast() = sendBroadcast(Intent(this,T::class.java))

fun Context.getBooleanPreference(key: String) = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(key, false)

fun Context.setBooleanPreference(key: String, value: Boolean) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .commit()


fun Context.isOnline() : Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    // if this is present, then check whether I am online or not
    connectivityManager.activeNetwork?.let { network ->
        connectivityManager.getNetworkCapabilities(network)?.let { capabilities ->
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    }
    return false
}

fun Context.showToast(text: String, length: Int) = Toast.makeText(this, text, length).show()