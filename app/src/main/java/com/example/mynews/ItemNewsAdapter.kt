package com.example.mynews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news.view.*


class ItemNewsAdapter(val topStoriesScience: JSONParserTopStories.TopStoriesScience, val itemClickListener: View.OnClickListener )
    : RecyclerView.Adapter<ItemNewsAdapter.ViewHolder>()  {

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.card_view)
        val iconView = itemView.findViewById<ImageView>(R.id.icon)
        val sectionView = cardView.findViewById<TextView>(R.id.section)
        val subsectionView = cardView.findViewById<TextView>(R.id.subsection)
        val titleView = cardView.findViewById<TextView>(R.id.title)
        val dateView = cardView.findViewById<TextView>(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(viewItem)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topStoriesScience  = topStoriesScience[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        holder.sectionView.text = topStoriesScience.section
        holder.subsectionView.text = topStoriesScience.subsection
        holder.titleView.text = topStoriesScience.title
        holder.dateView.text = topStoriesScience.date
        holder.iconView.setImageResource(R.mipmap.ic_launcher_round)
    }

    override fun getItemCount(): Int {
        return topStoriesScience.size

    }



}

