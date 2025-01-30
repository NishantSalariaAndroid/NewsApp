package com.example.newdemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

object CheckNetwork {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Get the current active network
        val network: Network? = connectivityManager.activeNetwork

        // Get network capabilities
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        // Check if the network is connected
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}