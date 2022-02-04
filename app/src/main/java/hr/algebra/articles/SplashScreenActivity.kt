package hr.algebra.articles

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.articles.utils.*
import kotlinx.android.synthetic.main.activity_splash_screen.*

private const val DELAY = 3000L
const val DATA_IMPORTED = "hr.algebra.articles.data_imported4"
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        startAnimations()
        redirect()
    }

    private fun startAnimations() {
        tvSplash.applyAnimation(R.anim.blink)
        ivSplash.applyAnimation(R.anim.rotate)
    }

    private fun redirect() {
        if (getBooleanPreference(DATA_IMPORTED)){
            Handler(Looper.getMainLooper()).postDelayed({ startActivity<HostActivity>() },DELAY)
        } else {
            if (isOnline()) {
                Intent(this, MoviesService::class.java).apply {
                    MoviesService.enqueueWork(this@SplashScreenActivity, this)
                }
            } else {
                showToast("Please connect to the internet", Toast.LENGTH_LONG)
                finish() // this guys kills activity
            }
        }
    }
}