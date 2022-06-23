package com.example.wave.ui

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.wave.databinding.ActivityMainBinding
import com.example.wave.network.rxmessages.ReloadPeopleList
import com.example.wave.utils.RxBus
import com.example.wave.utils.connectivity.NetworkCallback
import com.example.wave.utils.connectivity.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val networkConnection = NetworkConnection
    lateinit var views: ActivityMainBinding

    private val networkCallback = object : NetworkCallback(networkConnection) {
        override fun onAvailable(network: Network) {
            runOnUiThread {
                views.viewNoInternet.isVisible = false
            }
            RxBus.publish(ReloadPeopleList(true))
            super.onAvailable(network)
        }

        override fun onLost(network: Network) {
            runOnUiThread {
                views.viewNoInternet.isVisible = true
            }
            super.onLost(network)
        }
    }

    private fun registerNetworkCallback() {
        val connectivityManager = getSystemService(ConnectivityManager::class.java)
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().build(),
            networkCallback
        )
    }

    private fun unregisterNetworkCallback() {
        val connectivityManager = getSystemService(ConnectivityManager::class.java)
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityMainBinding.inflate(layoutInflater)
        setContentView(views.root)
        registerNetworkCallback()
    }

    override fun onDestroy() {
        unregisterNetworkCallback()
        super.onDestroy()
    }
}