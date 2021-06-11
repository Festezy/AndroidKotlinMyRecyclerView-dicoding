package com.ariqandrean.myrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ariqandrean.myrecyclerview.R
import com.ariqandrean.myrecyclerview.model.Hero
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewHeroAdapter(val listHero: ArrayList<Hero>) : RecyclerView.Adapter<CardViewHeroAdapter.CardViewViewHolder>() {
    inner class CardViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgPhoto = itemView.findViewById<ImageView>(R.id.img_item_photo)
        var tvName = itemView.findViewById<TextView>(R.id.tv_item_name)
        var tvDetail = itemView.findViewById<TextView>(R.id.tv_item_detail)
        var btnFavorite = itemView.findViewById<Button>(R.id.btn_set_favorite)
        var btnShare = itemView.findViewById<Button>(R.id.btn_set_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_hero, parent, false)
        return CardViewViewHolder(view)
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

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val hero = listHero[position]

        /** method for load the images*/
        Glide.with(holder.itemView.context) //For input context
            .load(hero.photo) //Used for input image from Url or from drawable
            .apply(RequestOptions().override(350, 550)) //For manipulating an image
            .into(holder.imgPhoto) //Used for input imageView as an object images

        holder.tvName.text = hero.name
        holder.tvDetail.text = hero.detail

        holder.btnFavorite.setOnClickListener {
            Toast.makeText(holder.itemView.context,
                "Favorite " + listHero[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()}
        holder.btnShare.setOnClickListener {
            Toast.makeText(holder.itemView.context,
                "Share " + listHero[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()}

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context,
                "Kamu memilih " + listHero[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()}
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listHero[holder.adapterPosition])
        }
    }
}