package com.example.grocerylistapp.ui.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class ImageUtils {
    companion object {
        fun readBytesFromUri(uri: Uri, context: Context): ByteArray? {
            var inputStream: InputStream? = null
            var byteArrayOutputStream: ByteArrayOutputStream? = null

            try {
                inputStream = context.contentResolver.openInputStream(uri)
                byteArrayOutputStream = ByteArrayOutputStream()

                if (inputStream != null) {
                    val options = BitmapFactory.Options()
                    options.inSampleSize = calculateInSampleSize(uri, context)
                    val originalBitmap = BitmapFactory.decodeStream(inputStream, null, options)

                    val orientation = getOrientation(uri, context)

                    val rotatedBitmap = rotateBitmap(originalBitmap, orientation)

                    rotatedBitmap?.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)

                    return byteArrayOutputStream.toByteArray()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                inputStream?.close()
                byteArrayOutputStream?.close()
            }

            return null
        }

        private fun calculateInSampleSize(uri: Uri, context: Context): Int {
            return 1
        }

        private fun getOrientation(uri: Uri, context: Context): Int {
            var inputStream: InputStream? = null

            try {
                inputStream = context.contentResolver.openInputStream(uri)

                if (inputStream != null) {
                    val exif = ExifInterface(inputStream)
                    return exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                inputStream?.close()
            }

            return ExifInterface.ORIENTATION_UNDEFINED
        }

        private fun rotateBitmap(bitmap: Bitmap?, orientation: Int): Bitmap? {
            if (bitmap == null) return null

            val matrix = Matrix()
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
            }

            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }
    }
}