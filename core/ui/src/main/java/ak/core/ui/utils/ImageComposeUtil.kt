package ak.core.ui.utils

import android.util.Log

import androidx.compose.material.icons.Icons

import androidx.compose.ui.graphics.vector.ImageVector


object ImageComposeUtil {
    fun createImageVector(name: String): ImageVector? {
        try {
            val className = "androidx.compose.material.icons.Outlined.${name}Kt"
            val cl = Class.forName(className)
            val method = cl.declaredMethods.first()
            return method.invoke(null, Icons.Outlined) as ImageVector
        } catch (ex: Exception) {
            Log.e("ImageNotFound", name)
            return null
        }
    }




}