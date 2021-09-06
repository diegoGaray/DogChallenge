package com.mobdev.dogceochallenge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.mobdev.dogceochallenge.R
import com.mobdev.dogceochallenge.model.Images
import com.squareup.picasso.Picasso

class AdapterImage(private val context: Context, private val imagesList: MutableList<Images?>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return imagesList.size
    }

    override fun getItem(pos: Int): Any {
        return imagesList[pos]!!
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.images, parent, false)
        }
        val img = view!!.findViewById<ImageView>(R.id.imageView)
        val thisContact = imagesList[position]
        Picasso.get().load(thisContact!!.image).into(img)
        return view
    }
}