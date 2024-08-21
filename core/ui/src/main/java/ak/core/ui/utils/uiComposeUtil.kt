package ak.core.ui.utils

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow

object uiComposeUtil {

    @Composable
    fun <T> rememberFlowWithLifecycle(
        flow: Flow<T>,
        lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    ): Flow<T> = remember(flow, lifecycle) {
        flow.flowWithLifecycle(
            lifecycle = lifecycle,
            minActiveState = minActiveState
        )
    }

    @Composable
    fun getAnimateFloat(): State<Float> {
        val infiniteTransition = rememberInfiniteTransition(label = "")
        return infiniteTransition.animateFloat(
            initialValue = 24.0f,
            targetValue = 48.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 800,
                    delayMillis = 100,
                    easing = FastOutLinearInEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )
    }

    @Composable
    fun getCurrentOrientation(): Int {
        val conf = LocalConfiguration.current
        return when (conf.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Configuration.ORIENTATION_LANDSCAPE
            }

            else -> {
                Configuration.ORIENTATION_PORTRAIT
            }
        }
    }
}