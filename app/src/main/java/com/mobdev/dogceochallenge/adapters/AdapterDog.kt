package com.mobdev.dogceochallenge.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.mobdev.dogceochallenge.ImagesActivity
import com.mobdev.dogceochallenge.R
import com.mobdev.dogceochallenge.modelo.Dogs

class AdapterDog(private val context: Context, private val dogsList: List<Dogs>) : BaseAdapter() {
    override fun getCount(): Int {
        return dogsList.size
    }

    override fun getItem(pos: Int): Any {
        return dogsList[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.dogs, parent, false)
        }
        val textName = view!!.findViewById<TextView>(R.id.textName)
        val dogs = dogsList[position]
        textName.text = dogs.raza
        view.setOnClickListener {
            Toast.makeText(context, dogs.raza, Toast.LENGTH_SHORT).show()
            val contacts = arrayOf(
                dogs.raza
            )
            openNextActivity(contacts)
        }
        return view
    }

    private fun openNextActivity(data: Array<String?>) {
        val nextActivity = Intent(context, ImagesActivity::class.java)
        nextActivity.putExtra("NAME", data[0])
        context.startActivity(nextActivity)
    }
}