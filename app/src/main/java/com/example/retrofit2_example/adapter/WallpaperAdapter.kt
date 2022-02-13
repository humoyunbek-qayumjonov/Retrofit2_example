package com.example.retrofit2_example.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.retrofit2_example.ImageFragment
import com.example.retrofit2_example.model.PagerModel

class WallpaperAdapter(var list:ArrayList<PagerModel>,fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return ImageFragment.newInstance(list[position].titleName)
    }
}