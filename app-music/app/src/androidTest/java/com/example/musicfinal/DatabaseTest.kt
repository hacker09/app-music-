package com.example.musicfinal

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.musicfinal.database.AppDataHelper
import com.example.musicfinal.database.model.Playlist
import com.example.musicfinal.database.model.Song
import com.example.musicfinal.database.updater.PlaylistUpdater
import com.example.musicfinal.database.updater.SongUpdater
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private val dataHelper: AppDataHelper =
            AppDataHelper.getInstance(InstrumentationRegistry.getTargetContext())

    @Test
    fun insertSong() {
        val song1 = Song(title = "a", id = 1, artist = "aa")
        val song2 = Song(title = "b", id = 2, artist = "bb")
        val song3 = Song(title = "c", id = 3, artist = "cc")
        dataHelper.addSong(song1)
        dataHelper.addSong(song2)
        dataHelper.addSong(song3)
        dataHelper.getAllSong(object : SongUpdater {
            override fun getSongResult(result: List<Song>) {
                assert(result.size == 3)
            }
        })
    }

    @Test
    fun deleteSong() {
        val song1 = Song(title = "a", id = 1, artist = "aa")
        val song2 = Song(title = "b", id = 2, artist = "bb")
        val song3 = Song(title = "c", id = 3, artist = "cc")
        dataHelper.addSong(song1)
        dataHelper.addSong(song2)
        dataHelper.addSong(song3)
        dataHelper.deleteSongWithId(1)
        dataHelper.getAllSong(object : SongUpdater {
            override fun getSongResult(result: List<Song>) {
                assert(result.size == 2)
                assert(result[0].id == 2L)
            }

        })
    }

    @Test
    fun addPlaylist() {
        val playlist1 = Playlist(name = "p1")
        val playlist2 = Playlist(name = "p2")
        dataHelper.addPlaylist(playlist1)
        dataHelper.addPlaylist(playlist2)
        dataHelper.getAllPlaylist(object : PlaylistUpdater {
            override fun getPlaylistResult(result: List<Playlist>) {
                assert(result.size == 2)
                assert(result[0].name == "p1")
            }
        })
    }

    @Test
    fun deletePlaylist() {
        val playlist1 = Playlist(name = "p1")
        val playlist2 = Playlist(name = "p2")
        dataHelper.addPlaylist(playlist1)
        dataHelper.addPlaylist(playlist2)
        dataHelper.deletePlaylist("p1")
        dataHelper.getAllPlaylist(object : PlaylistUpdater {
            override fun getPlaylistResult(result: List<Playlist>) {
                assert(result.size == 2)
                assert(result[0].name == "p2")
            }
        })
    }

    @Test
    fun addSongToPlaylist() {
        val song1 = Song(title = "a", id = 1, artist = "aa")
        val song2 = Song(title = "b", id = 2, artist = "bb")
        val song3 = Song(title = "c", id = 3, artist = "cc")
        dataHelper.addSong(song1)
        dataHelper.addSong(song2)
        dataHelper.addSong(song3)
        val playlist1 = Playlist(name = "p1")
        val playlist2 = Playlist(name = "p2")
        dataHelper.addPlaylist(playlist1)
        dataHelper.addPlaylist(playlist2)
        dataHelper.addSongToPlaylist("p1", 2)
        dataHelper.addSongToPlaylist("p1", 3)
        dataHelper.getSongInPlaylist("p1", object : SongUpdater {
            override fun getSongResult(result: List<Song>) {
                assert(result.size == 2)
                assert(result[0].id == 2L)
            }
        })
        dataHelper.getSongInPlaylist("p2", object : SongUpdater {
            override fun getSongResult(result: List<Song>) {
                assert(result.isEmpty())
            }
        })
    }

    @Test
    fun deleteSongInPlaylist() {
        val song1 = Song(title = "a", id = 1, artist = "aa")
        val song2 = Song(title = "b", id = 2, artist = "bb")
        val song3 = Song(title = "c", id = 3, artist = "cc")
        dataHelper.addSong(song1)
        dataHelper.addSong(song2)
        dataHelper.addSong(song3)
        val playlist1 = Playlist(name = "p1")
        val playlist2 = Playlist(name = "p2")
        dataHelper.addPlaylist(playlist1)
        dataHelper.addPlaylist(playlist2)
        dataHelper.addSongToPlaylist("p1", 2)
        dataHelper.addSongToPlaylist("p1", 3)
        dataHelper.deleteSongInPlaylist("p1", 2)
        dataHelper.getSongInPlaylist("p1", object : SongUpdater {
            override fun getSongResult(result: List<Song>) {
                assert(result.size == 1)
                assert(result[0].id == 3L)
            }
        })
    }

    @Test
    fun deletePlaylistAfterAdd() {
        val song1 = Song(title = "a", id = 1, artist = "aa")
        val song2 = Song(title = "b", id = 2, artist = "bb")
        val song3 = Song(title = "c", id = 3, artist = "cc")
        dataHelper.addSong(song1)
        dataHelper.addSong(song2)
        dataHelper.addSong(song3)
        val playlist1 = Playlist(name = "p1")
        val playlist2 = Playlist(name = "p2")
        dataHelper.addPlaylist(playlist1)
        dataHelper.addPlaylist(playlist2)
        dataHelper.addSongToPlaylist("p1", 2)
        dataHelper.addSongToPlaylist("p1", 3)
        dataHelper.deletePlaylist("p1")
        dataHelper.getSongInPlaylist("p1", object : SongUpdater {
            override fun getSongResult(result: List<Song>) {
                assert(result.isEmpty())
            }
        })
    }
}
