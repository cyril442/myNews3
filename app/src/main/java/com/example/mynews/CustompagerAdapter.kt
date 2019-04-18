package com.example.mynews

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CustompagerAdapter (fm : FragmentManager) : FragmentPagerAdapter(fm) {

    var mFm = fm
    var mFragmentItems : ArrayList<Fragment> = ArrayList()
    var mFragmentTitles : ArrayList<String> = ArrayList()

    fun addFragments (fragmentItem : Fragment, fragmentTitle : String){
        mFragmentItems.add(fragmentItem)
        mFragmentTitles.add(fragmentTitle)
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentItems[position]
    }

    override fun getCount(): Int {
        return mFragmentItems.size
    }

    // Need to override the getPageTitle to set the text on the tabs

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitles[position]
    }
}