package hr.algebra.articles.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.articles.MoviesContract
import hr.algebra.articles.R
import hr.algebra.articles.model.MoviesModelImpl
import hr.algebra.articles.presenter.MoviesPresenterImpl
import kotlinx.android.synthetic.main.fragment_articles.*

class MoviesFragment: Fragment(), MoviesContract.ListMoviesView, MovieTouchHelperCallback.OnSwipedListener {

    private lateinit var presenter: MoviesContract.MoviesPresenter
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initRecycler()
        initTouchHelper()
    }

    private fun initTouchHelper() {
        MovieTouchHelperCallback(0, ItemTouchHelper.LEFT, this).apply {
            ItemTouchHelper(this).attachToRecyclerView(rvMovies)
        }
    }

    private fun initPresenter() {
        presenter = MoviesPresenterImpl(context, this, MoviesModelImpl(context))
        presenter.requestToLoadMovies()
    }

    private fun initRecycler() {
        rvMovies.layoutManager = LinearLayoutManager(context)
        adapter = MoviesAdapter(presenter)
        rvMovies.adapter = adapter
    }


    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun showMovies() {
        progressBar.visibility = View.GONE
    }

    override fun onSwiped(position: Int) {
        presenter.delete(position)
    }


}