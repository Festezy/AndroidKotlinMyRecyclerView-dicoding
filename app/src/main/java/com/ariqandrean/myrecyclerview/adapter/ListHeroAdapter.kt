package com.ariqandrean.myrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ariqandrean.myrecyclerview.R
import com.ariqandrean.myrecyclerview.model.Hero
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.security.auth.callback.Callback

class ListHeroAdapter(val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tv_item_name)
        val tvDetail = itemView.findViewById<TextView>(R.id.tv_item_detail)
        val imgPhoto = itemView.findViewById<ImageView>(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_hero, viewGroup, false)
        return ListViewHolder(view)
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

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val hero = listHero[position]

        /** method for load the images*/
        Glide.with(holder.itemView.context) //For input context
            .load(hero.photo) //Used for input image from Url or from drawable
            .apply(RequestOptions().override(55, 55)) //For manipulating an image
            .into(holder.imgPhoto) //Used for input imageView as an object images

        holder.tvName.text = hero.name
        holder.tvDetail.text = hero.detail
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listHero[holder.adapterPosition])
        }
    }
}