package com.example.musicfinal.ui.viewpageradapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.musicfinal.ui.fragment.LibraryFragment
import com.example.musicfinal.ui.fragment.PlaylistFragment

class MainPagerAdapter(manager: FragmentManager?) : FragmentStatePagerAdapter(manager) {
    private val tabTitles = arrayOf("PLAYLIST", "LIBRARY")
    private val listFragment: MutableList<Fragment> = mutableListOf()

    init {
        addFragment()
    }

    private fun addFragment() {
        listFragment.add(PlaylistFragment())
        listFragment.add(LibraryFragment())
    }

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}
