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
import com.kylix.submissionbajp3.ui.adapter.FavoriteTvShowAdapter
import com.kylix.submissionbajp3.utils.Status
import com.kylix.submissionbajp3.utils.ViewModelFactory
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*

class FavoriteTvShowFragment : Fragment() {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val tvShowsAdapter = FavoriteTvShowAdapter(context)
        tvShowViewModel.getTvShowPaged.observe(viewLifecycleOwner, Observer { response ->

            if (response != null) {
                when (response.status) {
                    Status.LOADING -> { }
                    Status.SUCCESS -> {
                        favorite_tv_progress_bar.visibility = View.GONE
                        tvShowsAdapter.submitList(response.data)
                    }
                    Status.ERROR -> {
                        favorite_tv_progress_bar.visibility = View.GONE
                        FancyToast.makeText(context, getString(R.string.error), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
                    }
                }
            }

        })

        rv_favorite_tv_show.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = tvShowsAdapter
        }
    }
}