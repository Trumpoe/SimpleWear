package com.thewizrd.shared_resources.helpers

import android.net.Uri
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.wearable.PutDataRequest
import com.thewizrd.shared_resources.SimpleLibrary
import com.thewizrd.shared_resources.utils.Logger

object WearableHelper {
    // Name of capability listed in Phone app's wear.xml
    const val CAPABILITY_PHONE_APP = "com.thewizrd.simplewear_phone_app"

    // Name of capability listed in Wear app's wear.xml
    const val CAPABILITY_WEAR_APP = "com.thewizrd.simplewear_wear_app"

    // Link to Play Store listing
    private const val PLAY_STORE_APP_URI = "market://details?id=com.thewizrd.simplewear"

    val playStoreURI: Uri
        get() = Uri.parse(PLAY_STORE_APP_URI)

    // For WearableListenerService
    const val StartActivityPath = "/start-activity"
    const val AppStatePath = "/app_state"
    const val ActionsPath = "/actions"
    const val StatusPath = "/status"
    const val BatteryPath = "/status/battery"
    const val BluetoothPath = "/status/bt"
    const val WifiPath = "/status/wifi"
    const val UpdatePath = "/update/all"
    const val MusicPlayersPath = "/music-players"
    const val PlayCommandPath = "/music/play"
    const val OpenMusicPlayerPath = "/music/start-activity"
    const val BtDiscoverPath = "/bluetooth/discoverable"
    const val PingPath = "/ping"

    // For Music Player DataMap
    const val KEY_SUPPORTEDPLAYERS = "key_supported_players"
    const val KEY_LABEL = "key_label"
    const val KEY_ICON = "key_icon"
    const val KEY_PKGNAME = "key_package_name"
    const val KEY_ACTIVITYNAME = "key_activity_name"

    val isGooglePlayServicesInstalled: Boolean
        get() {
            val queryResult = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(SimpleLibrary.getInstance().app.appContext)
            if (queryResult == ConnectionResult.SUCCESS) {
                Logger.writeLine(Log.INFO, "App: Google Play Services is installed on this device.")
                return true
            }
            if (GoogleApiAvailability.getInstance().isUserResolvableError(queryResult)) {
                val errorString = GoogleApiAvailability.getInstance().getErrorString(queryResult)
                Logger.writeLine(Log.INFO,
                        "App: There is a problem with Google Play Services on this device: %s - %s",
                        queryResult, errorString)
            }
            return false
        }

    fun getWearDataUri(NodeId: String?, Path: String?): Uri {
        return Uri.Builder()
                .scheme(PutDataRequest.WEAR_URI_SCHEME)
                .authority(NodeId)
                .path(Path)
                .build()
    }
}