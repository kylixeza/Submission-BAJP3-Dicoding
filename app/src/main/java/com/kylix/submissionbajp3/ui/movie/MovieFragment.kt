@file:Suppress("DEPRECATION")

package com.kylix.submissionbajp3.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.kylix.submissionbajp3.R
import com.kylix.submissionbajp3.model.local.entity.MovieModel
import com.kylix.submissionbajp3.ui.adapter.MovieAdapter
import com.kylix.submissionbajp3.utils.Status
import com.kylix.submissionbajp3.utils.ViewModelFactory
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : Fragment() {

    private var movieList : List<MovieModel> = listOf()

    private val movieViewModel by lazy {
        val viewModelFactory= activity?.application?.let { ViewModelFactory.getInstance(it) }
        ViewModelProviders.of(this,viewModelFactory).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movieAdapter = MovieAdapter(context)
        movieViewModel.movies.observe(viewLifecycleOwner, Observer {
            movie_progress_bar.visibility = View.GONE
            movieList = it
            movieAdapter.getList(movieList)

            getMovies()
        })
        rv_movie.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieAdapter
        }
    }

    private fun getMovies() {
        movieViewModel.getMovies.observe(viewLifecycleOwner, Observer {

            when (it.status) {
                Status.SUCCESS -> {
                    movie_progress_bar.visibility = View.GONE
                    if (it.data.isNullOrEmpty()) {
                        movieViewModel.insertMovies(movieList)
                    }
                }
                Status.ERROR -> movie_progress_bar.visibility = View.GONE
                Status.LOADING -> {}
            }
        })
    }
}