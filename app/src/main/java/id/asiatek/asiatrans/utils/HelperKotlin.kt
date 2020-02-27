package id.asiatek.asiatrans.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

object HelperKotlin {
    fun getFile (context: Context, bitmap: Bitmap) : File {
        var f = File(context.getCacheDir(), "file_photo.jpg")
        f.createNewFile()
        var bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bos)
        var bitmapdata = bos.toByteArray()
        var fos = FileOutputStream(f)
        fos.write(bitmapdata)
        fos.flush()
        fos.close()
        return f
    }

     fun randomInRange(min:Int, max:Int):Int{
        val r = Random()
        return r.nextInt((max - min) + 1) + min;
    }
}