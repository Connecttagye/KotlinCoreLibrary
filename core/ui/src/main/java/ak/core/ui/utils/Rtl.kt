package ak.core.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

/**
 * Returns the correct icon based on the current layout direction.
 */
@Composable
fun mirroringIcon(ltrIcon: ImageVector, rtlIcon: ImageVector): ImageVector =
    if (LocalLayoutDirection.current == LayoutDirection.Ltr) ltrIcon else rtlIcon

/**
 * Returns the correct back navigation icon based on the current layout direction.
 */
@Composable
fun mirroringBackIcon() = mirroringIcon(
    ltrIcon = Icons.AutoMirrored.Outlined.ArrowBack, rtlIcon = Icons.AutoMirrored.Outlined.ArrowForward
)
