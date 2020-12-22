package com.kylix.submissionbajp3.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class FavoriteViewPagerAdapter internal constructor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val fragmentList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = titleList.size

    override fun getPageTitle(position: Int): CharSequence? = titleList[position]

    fun addFragment(fragment: Fragment, title : String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }
}