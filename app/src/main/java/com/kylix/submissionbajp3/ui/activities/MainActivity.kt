package com.kylix.submissionbajp3.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kylix.submissionbajp3.R
import com.kylix.submissionbajp3.ui.FavoriteFragment
import com.kylix.submissionbajp3.ui.movie.MovieFragment
import com.kylix.submissionbajp3.ui.setting.SettingsFragment
import com.kylix.submissionbajp3.ui.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_movie ->loadFragment(MovieFragment())
            R.id.nav_tvShow -> loadFragment(TvShowFragment())
            R.id.nav_favorite -> loadFragment(FavoriteFragment())
            R.id.nav_setting -> loadFragment(SettingsFragment())
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = getString(R.string.app_name)
        loadFragment(MovieFragment())
        bottom_nav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fm_container, fragment)
                .commit()
            return true
        }
        return false
    }
}