package com.example.homework5_92.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2_example.R
import com.example.retrofit2_example.model.ImageModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_item_layout.view.*

class RvAdapters(var list: ArrayList<ImageModel>,var onMyItemClickListener: OnMyItemClickListener):RecyclerView.Adapter<RvAdapters.MyViewHolder>() {
    inner class MyViewHolder(var itemView:View):RecyclerView.ViewHolder(itemView){
        fun onBind(image: ImageModel){
            Picasso.get().load(image.urls.regular).into(itemView.image_view)

            itemView.setOnClickListener {
                onMyItemClickListener.onMyItemClick(image)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView:View =
            LayoutInflater.from(parent.context).inflate(R.layout.image_item_layout,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyItemClickListener{
        fun onMyItemClick(image: ImageModel)
    }
}