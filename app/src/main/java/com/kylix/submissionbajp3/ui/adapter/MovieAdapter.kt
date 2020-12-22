package com.kylix.submissionbajp3.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kylix.submissionbajp3.R
import com.kylix.submissionbajp3.model.local.entity.MovieModel
import com.kylix.submissionbajp3.ui.activities.DetailActivity
import com.kylix.submissionbajp3.ui.activities.DetailActivity.Companion.TITLE
import kotlinx.android.synthetic.main.item_list.view.*

class MovieAdapter(private val context: Context? ) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var listMovies : List<MovieModel> = emptyList()
    fun getList(movies: List<MovieModel>) {
        this.listMovies = movies
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent,false))
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(listMovies[position])
        context?.let {
            Glide.with(it)
                .load(context.resources
                    .getIdentifier(listMovies[position].moviePoster, "drawable", context.packageName))
                .into(holder.poster)
        }
        holder.cardItem.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("movieId", listMovies[position].movieId)
            intent.apply {
                putExtra("movieId", listMovies[position].movieId)
                putExtra(TITLE, listMovies[position].movieTitle)
            }
            context?.startActivity(intent)
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val poster = itemView.iv_poster_list!!
        val cardItem = itemView.cv_list!!
        fun bindViewHolder(listMovies : MovieModel){
            itemView.tv_title_list.text = listMovies.movieTitle
        }
    }
}