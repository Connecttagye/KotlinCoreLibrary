package ak.core.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



object NetworkComposeUtils {


    @Composable
    fun ConnectivityStatusChecker(context : Context,onConnectivityChange: (Boolean) -> Unit) {

        var isConnected by remember { mutableStateOf(true) } // Initial state

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isConnected = true
                onConnectivityChange(true)
            }

            override fun onLost(network: Network) {
                isConnected = false
                onConnectivityChange(false)
            }
        }

        DisposableEffect(connectivityManager) {
            connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
            onDispose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }
    }

    @Composable
    fun ConnectivityStatusChecker(context :Context): StateFlow<Boolean> {
        val isConnected = remember { MutableStateFlow(false) } // Initial state as false

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                // Check if the network has internet connectivity
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                isConnected.value = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
            }

            override fun onLost(network: Network) {
                isConnected.value = false
            }
        }

        DisposableEffect(connectivityManager) {
            connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
            onDispose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }

        return isConnected
    }

}