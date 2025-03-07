package com.edsonlimadev.appcontact.extensions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

fun mediaPermission() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    Manifest.permission.READ_MEDIA_IMAGES
} else {
    Manifest.permission.READ_EXTERNAL_STORAGE
}

fun cameraPermission() = Manifest.permission.CAMERA

fun Context.checkPermission(permission: String): Boolean {
   return (ContextCompat.checkSelfPermission(this, permission)
            == PackageManager.PERMISSION_GRANTED)
}

fun Context.createImageUri(context: Context): Uri? {
    val imageFileName = UUID.randomUUID().toString()
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val image = File.createTempFile(imageFileName, ".jpg", storageDir).apply {
        deleteOnExit()
    }

    return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", image)
}

fun Context.persistUriPermission(uri: Uri) {
    val contentResolver = contentResolver
    val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION
    contentResolver.takePersistableUriPermission(uri, takeFlags)
}
