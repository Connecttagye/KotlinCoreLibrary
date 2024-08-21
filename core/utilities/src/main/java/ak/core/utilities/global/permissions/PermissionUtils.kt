package com.core.utils.utilities.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale

object PermissionUtils {

    private const val PERMISSION_REQUEST_CODE = 2000

    /**
     * Check if application has a permission or not
     */
    internal fun Context?.isPermissionGranted(permission: String): Boolean = if (this != null) {
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    } else {
        false
    }

    /**
     * shows get permission page to user
     */
    internal fun Activity?.getPermission(permission: Array<String>) {
        if (this != null) {
            ActivityCompat.requestPermissions(this, permission, PERMISSION_REQUEST_CODE)
        } else {
            throw NullPointerException("Provided activity is null")
        }
    }

    /**
     * Shows the unknown source install page
     */
    internal fun Context.showRequest() {
        this.startActivity(
            Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
                .setData(Uri.parse(String.format(Locale.ENGLISH, "package:%s", this.packageName))),
        )
    }


    fun openManagePermissions(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        context.startActivity(intent)

    }

}