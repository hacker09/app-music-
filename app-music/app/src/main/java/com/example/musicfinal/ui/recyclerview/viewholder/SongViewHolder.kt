package com.example.musicfinal.ui.recyclerview.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.musicfinal.R
import com.example.musicfinal.ui.fragment.listener.ListSongFragmentActionListener

class SongViewHolder(itemView: View?,
                     private val listener: ListSongFragmentActionListener)
    : RecyclerView.ViewHolder(itemView) {
    var songId = 0L
    val tvTitleSong = itemView?.findViewById<TextView>(R.id.tvTitleSong)
    val tvSinger = itemView?.findViewById<TextView>(R.id.tvSinger)
    val imgFavourite = itemView?.findViewById<ImageView>(R.id.imgFavourite)

    init {
        imgFavourite?.setOnClickListener {
            listener.onFavoriteChange(songId)
        }

        itemView?.setOnClickListener {
            listener.onStartPlay(songId)
        }
    }
}
