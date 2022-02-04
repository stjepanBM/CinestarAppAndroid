package hr.algebra.articles.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.algebra.articles.R
import hr.algebra.articles.ViewMovieContract
import hr.algebra.articles.model.ViewMovieModelImpl
import hr.algebra.articles.presenter.MOVIE_POSITION
import hr.algebra.articles.presenter.ViewMoviePresenterImpl
import kotlinx.android.synthetic.main.activity_article_pager.*

class MoviePagerActivity : AppCompatActivity(), ViewMovieContract.MovieView {

    private lateinit var presenter: ViewMovieContract.ViewMoviePresenter
    private var initialPosition : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_pager)

        handleInitialPosition(intent)
        initPresenter()
        handleBackMenu()
    }

    private fun handleInitialPosition(intent: Intent?) {
        initialPosition = intent?.getIntExtra(MOVIE_POSITION, 0)
    }

    private fun initPresenter() {
        presenter = ViewMoviePresenterImpl(this, this, ViewMovieModelImpl(this))
        presenter.requestToLoadMovies()
    }

    private fun handleBackMenu() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // click on home up is going to go back
        return super.onSupportNavigateUp()
    }

    override fun showMovie() {
        if (viewPager.adapter == null) {
            viewPager.adapter = MoviePagerAdapter(presenter)
            viewPager.currentItem = initialPosition ?: 0
        } else {
            viewPager.adapter!!.notifyDataSetChanged()
        }
    }
}