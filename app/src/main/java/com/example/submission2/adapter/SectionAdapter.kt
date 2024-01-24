package com.example.submission2.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.submission2.R
import com.example.submission2.adapter.fragment.FollowersFragment
import com.example.submission2.adapter.fragment.FollowingFragment

class SectionAdapter(private val context: Context,fragMan :FragmentManager, data: Bundle):FragmentPagerAdapter(fragMan, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    private var fraBunl: Bundle

    init{
        fraBunl= data
    }
    @StringRes
    private val TAB= intArrayOf(R.string.tab_1, R.string.tab_2)
    override fun getCount(): Int =2



    override fun getItem(position: Int): Fragment {
        var frag: Fragment?=null
        when (position){
            0 -> frag= FollowersFragment()
            1 -> frag= FollowingFragment()

        }
        frag?.arguments=this.fraBunl
        return frag as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB[position])
    }
}