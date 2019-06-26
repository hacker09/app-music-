package com.example.musicfinal.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import java.io.ByteArrayOutputStream

fun bitmapToByteArray(image: Bitmap): ByteArray {
    val outputStream = ByteArrayOutputStream()
    image.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    return outputStream.toByteArray()
}

fun getBitmapFromImageView(imageView: ImageView): Bitmap {
    return (imageView.drawable as BitmapDrawable).bitmap
}
