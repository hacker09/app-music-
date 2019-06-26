package com.example.musicfinal.ui.recyclerview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.musicfinal.R
import com.example.musicfinal.database.model.Playlist
import com.example.musicfinal.ui.recyclerview.viewholder.PlaylistViewHolder
import com.example.musicfinal.ui.recyclerview.listener.PlaylistViewHolderListener

class PlaylistAdapter(private var playlists: MutableList<Playlist>,
                      val context: Context?,
                      private val listener: PlaylistViewHolderListener)
    : RecyclerView.Adapter<PlaylistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_playlist, parent, false)
        return PlaylistViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return playlists.size
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlists[position]
        holder.tvNamePlaylist?.text = playlist.name
        holder.index = position
    }
}
