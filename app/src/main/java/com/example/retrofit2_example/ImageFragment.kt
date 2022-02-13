package com.example.retrofit2_example

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework5_92.adapters.RvAdapters
import com.example.retrofit2_example.api.ApiUtilits
import com.example.retrofit2_example.model.ImageModel
import com.example.retrofit2_example.model.SearchModel
import kotlinx.android.synthetic.main.fragment_image2.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageFragment : Fragment() {

    interface onSomeEventListener {
        fun someEvent(s: String?)
    }

    var someEventListener: onSomeEventListener? = null
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        someEventListener = try {
            activity as onSomeEventListener
        } catch (e: Exception) {
            throw Exception("$activity must implement onSomeEventListener")
        }
    }


    lateinit var root:View
    lateinit var list:ArrayList<ImageModel>
    lateinit var rvAdapters: RvAdapters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_image2, container, false)
        //getData()

        list = ArrayList()
        ApiUtilits.getApiInterface().searchImage(param1!!,30).enqueue(object : Callback<SearchModel> {
            override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {
                list.clear()
                val results = response.body()?.results
                list = results!!

                rvAdapters = RvAdapters(list, object : RvAdapters.OnMyItemClickListener {
                    override fun onMyItemClick(image: ImageModel) {
                        someEventListener?.someEvent(image.urls.regular.toString())
                    }
                })
                root.recyclerView.adapter = rvAdapters
            }

            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${"onFailure"}")
            }

        })


        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ImageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}