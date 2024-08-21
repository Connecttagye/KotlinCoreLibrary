package ak.core.utilities.global

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import com.core.utils.utilities.permissions.PermissionUtils.getPermission
import com.core.utils.utilities.permissions.PermissionUtils.isPermissionGranted
import com.core.utils.utilities.permissions.PermissionUtils.showRequest

object DownloadUtils {
    fun downloadFile(context: Context, url: String, fileName: String): Long {
        val request = DownloadManager.Request(Uri.parse(url))
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
            .setTitle(fileName)
            .setDescription("جاري التنزيل...")

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        return downloadManager.enqueue(request)
    }

    fun getApk(url: String, appName: String, activity: Activity) {
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && !activity.packageManager.canRequestPackageInstalls()) {
            activity.showRequest()
        }

        if (activity.isPermissionGranted(permission) || Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {

        } else {
            activity.getPermission(arrayOf(permission))
        }
    }

}

