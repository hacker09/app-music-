package com.example.musicfinal.firebase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.musicfinal.model.SingletonUser
import com.example.musicfinal.model.User

object FirebaseDatabaseUtils {
    private const val TABLE_NAME = "Users"
    private const val AVATAR_COLUMN = "avatarPath"
    private val database = FirebaseDatabase.getInstance().reference

    fun getCurrentUser(mail: String, updater: DatabaseUpdater) {
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childDataSnapshot: DataSnapshot in dataSnapshot.children) {
                    val user = childDataSnapshot.getValue(User::class.java)
                    if (user?.mail == mail) {
                        SingletonUser.instance.idUser = user.idUser
                        SingletonUser.instance.mail = user.mail
                        SingletonUser.instance.name = user.name
                        SingletonUser.instance.password = user.password
                        SingletonUser.instance.age = user.age
                        SingletonUser.instance.avatar = user.avatarPath
                        SingletonUser.instance.latitude = user.latitude
                        SingletonUser.instance.longitude = user.longitude
                        updater.onComplete(user)
                        break
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TTT", error.message)
            }
        }

        database.child(TABLE_NAME).addListenerForSingleValueEvent(listener)
    }

    fun addUser(user: User) {
        database.child(TABLE_NAME).push().key?.let {
            user.idUser = it
            database.child(TABLE_NAME).child(it).setValue(user)
        }
    }

    fun updateImageReference(userId: String, url: String) {
        database.child(TABLE_NAME).child(userId).child(AVATAR_COLUMN).setValue(url)
    }

    fun updateUserInfo(userId: String, user: User) {
        database.child(TABLE_NAME).child(userId).setValue(user)
    }
}
