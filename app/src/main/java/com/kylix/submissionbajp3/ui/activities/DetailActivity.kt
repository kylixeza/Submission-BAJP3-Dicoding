@file:Suppress("DEPRECATION")

package com.kylix.submissionbajp3.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.appbar.AppBarLayout
import com.kylix.submissionbajp3.R
import com.kylix.submissionbajp3.model.local.entity.MovieModel
import com.kylix.submissionbajp3.model.local.entity.TvShowModel
import com.kylix.submissionbajp3.ui.movie.MovieViewModel
import com.kylix.submissionbajp3.ui.tvshow.TvShowViewModel
import com.kylix.submissionbajp3.utils.Resource
import com.kylix.submissionbajp3.utils.Status
import com.kylix.submissionbajp3.utils.ViewModelFactory
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private var menu: Menu? = null
    companion object{
        const val TITLE = "title"
    }

    private val movieDetailViewModel by lazy {
        val viewModelFactory= ViewModelFactory.getInstance(application)
        ViewModelProviders.of(this,viewModelFactory).get(MovieViewModel::class.java)
    }

    private val tvShowDetailViewModel by lazy {
        val viewModelFactory= ViewModelFactory.getInstance(application)
        ViewModelProviders.of(this,viewModelFactory).get(TvShowViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (collapseToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapseToolbar)) {
                toolbar.apply {
                    setBackgroundColor(Color.WHITE)
                    title = intent.getStringExtra(TITLE)
                    visibility = View.VISIBLE
                    setNavigationIcon(R.drawable.ic_back)
                }
                collapseToolbar.setCollapsedTitleTextColor(Color.BLACK)
            } else {
                toolbar.apply {
                    setBackgroundColor(Color.TRANSPARENT)
                    toolbar.visibility = View.VISIBLE
                    setNavigationIcon(R.drawable.ic_back)
                }
                collapseToolbar.setExpandedTitleColor(Color.TRANSPARENT)
            }
        })

        toolbar.setNavigationOnClickListener { onBackPressed() }

        if (intent.getIntExtra("movieId",0) != 0){
            movieDetailViewModel.movieDetail(intent.getIntExtra("movieId",0)).observe(this, Observer {
                initLoading()
                loadDataMovie(it)
                movieDetailViewModel.setMovieId(intent.getIntExtra("movieId",0))
            })
        }else{
            tvShowDetailViewModel.tvShowDetail(intent.getIntExtra("tvShowId",0)).observe(this, Observer {
                initLoading()
                loadDataTvShow(it)
                tvShowDetailViewModel.tvShowId.value =intent.getIntExtra("tvShowId",0)
            })
        }
    }

    private fun initLoading(){
        progress_bar.visibility = View.GONE
        coordinator.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.favorite_menu)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_unfavorite)
        }
    }

    private fun loadDataMovie(movie : MovieModel?){
        collapseToolbar.title = movie?.movieTitle
        Glide.with(this)
            .load(resources
                .getIdentifier(movie?.moviePoster, "drawable", packageName))
            .into(iv_big_poster)
        Glide.with(this)
            .load(resources
                .getIdentifier(movie?.moviePoster, "drawable", packageName))
            .into(iv_small_poster)
        tv_title.text = movie?.movieTitle
        tv_release.text = movie?.movieRelease
        tv_duration.text = movie?.movieDuration
        tv_genre.text = movie?.movieGenre
        tv_score.text = movie?.movieRating
        tv_overview.text = movie?.movieDescription
    }

    private fun loadDataTvShow(tvShow: TvShowModel?){
        collapseToolbar.title = tvShow?.tvShowTitle
        Glide.with(this)
            .load(resources
                .getIdentifier(tvShow?.tvShowPoster, "drawable", packageName))
            .into(iv_big_poster)
        Glide.with(this)
            .load(resources
                .getIdentifier(tvShow?.tvShowPoster, "drawable", packageName))
            .into(iv_small_poster)
        tv_title.text = tvShow?.tvShowTitle
        tv_release.text = tvShow?.tvShowRelease
        tv_duration.text = tvShow?.tvShowDuration
        tv_genre.text = tvShow?.tvShowGenre
        tv_score.text = tvShow?.tvShowRating
        tv_overview.text = tvShow?.tvShowDescription
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        this.menu = menu
        if (intent.getIntExtra("movieId",0) != 0) {
            movieDetailViewModel.getMovie.observe(this, Observer<Resource<MovieModel>> { response ->
                response?.let {
                    when (response.status) {
                        Status.LOADING -> { }
                        Status.SUCCESS -> {
                            initLoading()
                            response.data?.let {
                                val state = it.favorite
                                setFavoriteState(state)
                            }
                        }
                        Status.ERROR -> {
                            initLoading()
                            FancyToast.makeText(applicationContext, getString(R.string.error),
                                FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
                        }
                    }
                }
            })
        }else{
            tvShowDetailViewModel.getTvShow.observe(this, Observer { response ->
                response?.let {
                    when (response.status) {
                        Status.LOADING -> { }
                        Status.SUCCESS -> {
                            initLoading()
                            response.data?.let {
                                val state = it.favorite
                                setFavoriteState(state)
                            }
                        }
                        Status.ERROR -> {
                            initLoading()
                            FancyToast.makeText(applicationContext, getString(R.string.error),
                                FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
                        }
                    }
                }

            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorite_menu) {
            if (intent.getIntExtra("movieId",0) != 0) {
                movieDetailViewModel.setFavoriteMovie()
            }else{
                tvShowDetailViewModel.setFavoriteTvShow()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}