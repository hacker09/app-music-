package com.example.musicfinal.ui.recyclerview.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import com.example.musicfinal.R
import com.example.musicfinal.database.model.Song
import com.example.musicfinal.ui.recyclerview.listener.RadioButtonEventChoice

class PlaylistChoiceAdapter(private var listChoice: MutableList<Boolean>,
                            private var listSongs: MutableList<Song>,
                            private val listener: RadioButtonEventChoice)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_choice_song_add_playlist, parent, false)
        return ListSongsChoiceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listSongs.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val song = listSongs[position]
        holder as ListSongsChoiceViewHolder
        holder.tvNameSongListChoice.text = song.title
        holder.tvAristListChoice.text = song.artist
        holder.rbSonglistChoice.isChecked = listChoice[position]
    }

    inner class ListSongsChoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvNameSongListChoice: TextView = itemView.findViewById(R.id.tvItemNameSongListChoice)
        val tvAristListChoice: TextView = itemView.findViewById(R.id.tvSongArtistListChoice)
        val rbSonglistChoice: RadioButton = itemView.findViewById(R.id.rbSonglistChoice)
        val llPlaylistChoice: LinearLayout = itemView.findViewById(R.id.llPlaylistChoice)
        init {
            llPlaylistChoice.setOnClickListener {
                val isCheck = rbSonglistChoice.isChecked.not()
                rbSonglistChoice.isChecked = isCheck
                listener.onRadioButtonClickListener(adapterPosition, isCheck)
            }
        }
    }
}
