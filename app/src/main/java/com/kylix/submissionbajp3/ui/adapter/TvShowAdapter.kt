package com.kylix.submissionbajp3.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kylix.submissionbajp3.R
import com.kylix.submissionbajp3.model.local.entity.TvShowModel
import com.kylix.submissionbajp3.ui.activities.DetailActivity
import com.kylix.submissionbajp3.ui.activities.DetailActivity.Companion.TITLE
import kotlinx.android.synthetic.main.item_list.view.*

class TvShowAdapter(private val context: Context?) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private var listTvShows : List<TvShowModel> = emptyList()
    fun getList(tvShow: List<TvShowModel>) {
        this.listTvShows = tvShow
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent,false))
    }

    override fun getItemCount(): Int {
        return listTvShows.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(listTvShows[position])
        context?.let {
            Glide.with(it)
                .load(context.resources
                    .getIdentifier(listTvShows[position].tvShowPoster, "drawable", context.packageName))
                .into(holder.poster)
        }
        holder.cardItem.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("tvShowId", listTvShows[position].tvShowId)
            intent.apply {
                putExtra("tvShowId", listTvShows[position].tvShowId)
                putExtra(TITLE, listTvShows[position].tvShowTitle)
            }
            context?.startActivity(intent)
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val poster = itemView.iv_poster_list!!
        val cardItem = itemView.cv_list!!
        fun bindViewHolder(listMovies : TvShowModel){
            itemView.tv_title_list.text = listMovies.tvShowTitle
        }
    }
}