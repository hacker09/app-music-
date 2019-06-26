package com.example.musicfinal.ui.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicfinal.R
import com.example.musicfinal.database.AppDataHelper
import com.example.musicfinal.database.model.Playlist
import com.example.musicfinal.database.updater.PlaylistUpdater
import com.example.musicfinal.ui.activity.ListSongInPlaylistActivity
import com.example.musicfinal.ui.recyclerview.adapter.PlaylistAdapter
import com.example.musicfinal.ui.dialog.AddPlaylistDialog
import com.example.musicfinal.ui.recyclerview.listener.PlaylistViewHolderListener
import kotlinx.android.synthetic.main.fragment_playlist.*

@Suppress("DEPRECATION")
class PlaylistFragment : Fragment(), PlaylistViewHolderListener {
    private var playlists: MutableList<Playlist> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setListener()
        getPlayList()
    }

    fun getPlayList() {
        context?.let {
            AppDataHelper.getInstance(it).getAllPlaylist(object : PlaylistUpdater {
                override fun getPlaylistResult(result: List<Playlist>) {
                    playlists.clear()
                    playlists.addAll(result)
                    recyclerViewPlaylist.adapter.notifyDataSetChanged()
                }
            })
        }
    }

    private fun initViews() {
        recyclerViewPlaylist.layoutManager = LinearLayoutManager(activity)
        recyclerViewPlaylist.adapter = PlaylistAdapter(playlists, context, this)
    }

    private fun setListener() {
        imgAdd.setOnClickListener{
            AddPlaylistDialog().show(activity?.supportFragmentManager, AddPlaylistDialog.NAME)
        }
    }

    override fun addObjectOnClick(position: Int) {
        val namePlaylist = playlists[position].name
        val intent = Intent(context, ListSongInPlaylistActivity::class.java)
        intent.putExtra(KEY_ID_PLAYLIST_BUNDLE, namePlaylist)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PLAYLIST_REQUEST_CODE && resultCode == RESULT_OK) {
            recyclerViewPlaylist.adapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val ADD_PLAYLIST_REQUEST_CODE = 200
        const val KEY_ID_PLAYLIST_BUNDLE = "keyIdNamePlaylist"
    }
}
