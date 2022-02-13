package com.example.retrofit2_example

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.retrofit2_example.adapter.WallpaperAdapter
import com.example.retrofit2_example.api.ApiUtilits
import com.example.retrofit2_example.model.ImageModel
import com.example.retrofit2_example.model.PagerModel
import com.example.retrofit2_example.model.SearchModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),ImageFragment.onSomeEventListener{
    lateinit var categoryList:ArrayList<PagerModel>
    lateinit var wallpaperAdapter:WallpaperAdapter

    lateinit var list2:ArrayList<SearchModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        categoryList = ArrayList()
        categoryList.add(PagerModel("ALL"))
        categoryList.add(PagerModel("NEW"))
        categoryList.add(PagerModel("ANIMALS"))
        categoryList.add(PagerModel("TECHNOLOGY"))
        categoryList.add(PagerModel("NATURE"))
        wallpaperAdapter = WallpaperAdapter(categoryList,this)
        viewPager.adapter = wallpaperAdapter
        TabLayoutMediator(tabLayout,viewPager, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = categoryList[position].titleName
            }

        }).attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.tv_indicator?.visibility = View.VISIBLE
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.tv_indicator?.visibility = View.INVISIBLE
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.tv_indicator?.visibility = View.VISIBLE
            }
        })

        val tabcount = tabLayout.tabCount
        for (i in 0 until tabcount) {
            val tabView = LayoutInflater.from(this).inflate(R.layout.tab_item, null, false)
            val tab = tabLayout.getTabAt(i)
            tab?.customView = tabView
            tabView.tv_title.text = categoryList[i].titleName
            if (i == 0) {
                tabView.tv_indicator.visibility = View.VISIBLE

            } else {
                tabView.tv_indicator.visibility = View.INVISIBLE

            }
        }

    }

    override fun someEvent(s: String?) {
        val intent = Intent(this,SecondActivity::class.java)
        intent.putExtra("image1",s)
        startActivity(intent)
    }

}