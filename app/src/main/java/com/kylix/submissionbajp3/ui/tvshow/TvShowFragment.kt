@file:Suppress("DEPRECATION")

package com.kylix.submissionbajp3.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.kylix.submissionbajp3.R
import com.kylix.submissionbajp3.model.local.entity.TvShowModel
import com.kylix.submissionbajp3.ui.adapter.TvShowAdapter
import com.kylix.submissionbajp3.utils.Status
import com.kylix.submissionbajp3.utils.ViewModelFactory
import kotlinx.android.synthetic.main.tv_show_fragment.*

class TvShowFragment : Fragment() {

    private var tvShowList : List<TvShowModel> = listOf()

    private val tvShowViewModel by lazy {
        val viewModelFactory= activity?.application?.let {
            ViewModelFactory.getInstance(it)
        }
        ViewModelProviders.of(this,viewModelFactory).get(TvShowViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_show_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val tvShowsAdapter = TvShowAdapter(context)
        tvShowViewModel.tvShow.observe(viewLifecycleOwner, Observer {
            tvShow_progress_bar.visibility = View.GONE
            tvShowList = it
            tvShowsAdapter.getList(tvShowList)
            getTvShows()
        })
        rv_tvShow.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = tvShowsAdapter
        }
    }

    private fun getTvShows() {
        tvShowViewModel.getTvShows.observe(viewLifecycleOwner, Observer {

            when (it.status) {
                Status.SUCCESS -> {
                    tvShow_progress_bar.visibility = View.GONE
                    if (it.data.isNullOrEmpty()) {
                        tvShowViewModel.insertTvShows(tvShowList)
                    }
                }
                Status.ERROR -> tvShow_progress_bar.visibility = View.GONE
                Status.LOADING -> {}
            }
        })
    }

}