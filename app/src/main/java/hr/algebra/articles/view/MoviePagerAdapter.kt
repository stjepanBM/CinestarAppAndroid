package hr.algebra.articles.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.articles.R
import hr.algebra.articles.ViewMovieContract
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.view_article.view.*
import java.io.File

class MoviePagerAdapter(private val presenter: ViewMovieContract.ViewMoviePresenter)
    : RecyclerView.Adapter<MoviePagerAdapter.ViewHolder>(){

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ViewMovieContract.SingleMovieView{
            private val ivMovie = itemView.findViewById<ImageView>(R.id.ivMovie)
            val ivGenre = itemView.findViewById<TextView>(R.id.tvGenre)
            val ivTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            val ivDescription = itemView.findViewById<TextView>(R.id.tvDescription)
            val ivDirector = itemView.findViewById<TextView>(R.id.tvDirector)
            val ivDate = itemView.findViewById<TextView>(R.id.tvDate)

            override fun setTitle(title: String) {
                ivTitle.text = title
            }

            override fun setDescription(description: String) {
                ivDescription.text = description
            }

            override fun setDateTime(dateTime: String) {
                ivDate.text = dateTime
            }

            override fun setDirector(director: String) {
                ivDirector.text = director
            }

            override fun setGenre(genre: String) {
                ivGenre.text = genre
            }

            override fun setImage(path: String) {
                Picasso.get()
                    .load(File(path))
                    .transform(RoundedCornersTransformation(50,5))
                    .error(R.drawable.newspaper)
                    .into(ivMovie)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_article, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindSingleMovie(holder, position)
    }

    override fun getItemCount(): Int {
       return presenter.moviesCount
    }


}