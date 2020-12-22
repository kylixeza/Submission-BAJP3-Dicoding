package com.kylix.submissionbajp3.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kylix.submissionbajp3.R
import com.kylix.submissionbajp3.model.local.entity.TvShowModel
import com.kylix.submissionbajp3.ui.activities.DetailActivity
import kotlinx.android.synthetic.main.item_list.view.*

class FavoriteTvShowAdapter(private val context: Context? ) : PagedListAdapter<TvShowModel, FavoriteTvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowModel>() {
            override fun areItemsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.tvTitle.text = tvShow.tvShowTitle
            context?.let { Glide.with(it).load(context.resources
                .getIdentifier(tvShow.tvShowPoster, "drawable", context.packageName))
                .into(holder.poster) }
            holder.cardItem.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("tvShowId", tvShow.tvShowId)
                context?.startActivity(intent)
            }
        }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.iv_poster_list
        val cardItem: CardView = itemView.cv_list
        val tvTitle: TextView = itemView.tv_title_list

    }
}