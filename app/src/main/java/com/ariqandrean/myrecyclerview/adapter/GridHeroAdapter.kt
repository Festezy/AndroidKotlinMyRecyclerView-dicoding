package com.ariqandrean.myrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ariqandrean.myrecyclerview.R
import com.ariqandrean.myrecyclerview.model.Hero
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridHeroAdapter(val listHero: ArrayList<Hero>)
    : RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>() {
    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto = itemView.findViewById<ImageView>(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_hero, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHero.size
    }

    /** method setOnClickListener */
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    /** interface setOnClickListener */
    interface OnItemClickCallback {
        /** implementations onClick to interface class*/
        fun onItemClicked(data: Hero)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        /** method for load the images*/
        Glide.with(holder.itemView.context) //For input context
            .load(listHero[position].photo) //Used for input image from Url or from drawable
            .apply(RequestOptions().override(350, 550)) //For manipulating an image
            .into(holder.imgPhoto) //Used for input imageView as an object images

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listHero[holder.adapterPosition])
        }
    }
}