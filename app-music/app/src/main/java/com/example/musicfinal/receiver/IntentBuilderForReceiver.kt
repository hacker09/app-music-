package com.example.musicfinal.receiver

import android.content.Context
import android.content.Intent
import android.support.annotation.IntDef
import com.example.musicfinal.receiver.MusicReceiver.Companion.ACTION_UPDATE_RECEIVER

/**
 * a handmade intent for broadcast receiver
 *
 * To send a intent to music broadcast receiver:
 *
 * IntentBuilderForReceiver(context,IntentBuilderForReceiver.START_PLAY_SONG)
 *      .title("a").send()
 */
class IntentBuilderForReceiver(private val context: Context, private val intentType: Int) {
    private var title: String? = null
    private var duration: Int = -1
    private var currentTime: Int = -1

    fun title(title: String) = apply {
        this.title = title
    }

    fun duration(duration: Int) = apply {
        this.duration = duration
    }

    fun currentTime(currentTime: Int) = apply {
        this.currentTime = currentTime
    }

    fun send() = context.sendBroadcast(Intent(ACTION_UPDATE_RECEIVER).apply {
        putExtra(LETTER_TYPE_KEY, intentType)
        title?.let { putExtra(TITLE_KEY, title) }
        if (duration >= 0) {
            putExtra(DURATION_KEY, duration)
        }
        if (currentTime >= 0) {
            putExtra(CURRENT_TIME_KEY, currentTime)
        }
    })

    companion object {
        const val START_PLAY_SONG = 0
        const val UPDATE_TIME = 1
        const val PAUSE = 2
        const val UN_PAUSE = 3
        const val INVALID = -1

        private const val LETTER_TYPE_KEY = "LETTER_TYPE_KEY"
        private const val TITLE_KEY = "SONG_NAME_KEY"
        private const val DURATION_KEY = "DURATION_KEY"
        private const val CURRENT_TIME_KEY = "CURRENT_TIME_KEY"

        /**
         * to get letter's information when you receive a letter intent
         */
        internal fun getLetterInfo(intent: Intent): LetterInfo {
            val letterInfo = LetterInfo(@IntentType intent.getIntExtra(LETTER_TYPE_KEY, INVALID))
            intent.extras?.apply {
                if (this.containsKey(TITLE_KEY)) {
                    letterInfo.title = this.getString(TITLE_KEY)
                }
                if (this.containsKey(DURATION_KEY)) {
                    letterInfo.duration = this.getInt(DURATION_KEY)
                }
                if (this.containsKey(CURRENT_TIME_KEY)) {
                    letterInfo.currentTime = this.getInt(CURRENT_TIME_KEY)
                }
            }
            return letterInfo
        }
    }

    @Target(AnnotationTarget.EXPRESSION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY)
    @IntDef(START_PLAY_SONG, UPDATE_TIME, PAUSE, UN_PAUSE, INVALID)
    @Retention(AnnotationRetention.SOURCE)
    annotation class IntentType

    /**
     * the letter's information that broadcast receiver will receive
     */
    class LetterInfo(@IntentType val intentType: Int, var title: String = "",
                     var duration: Int = -1, var currentTime: Int = -1)
}
