package com.tatopane.chatgptandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsCallback
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.core.net.toUri
import android.graphics.Color
import android.content.ComponentName
//import androidx.appcompat.app.AppCompatActivity

class MainActivity : ComponentActivity() {
//class MainActivity : AppCompatActivity() {

    private lateinit var customTabsServiceConnection: CustomTabsServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ChatGPTAndroid)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchChromeCustomTab("https://chat.openai.com/?model=gpt-4")

    }

    override fun onResume() {
        setTheme(R.style.Theme_ChatGPTAndroid)
        super.onResume()
        setContentView(R.layout.activity_main)
        launchChromeCustomTab("https://chat.openai.com/?model=gpt-4")
    }

    private fun launchChromeCustomTab(url: String) {
        val colorSchemeParams = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(Color.parseColor("#353740"))
            .build()
        val customTabsIntent = CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(colorSchemeParams)
            .build()
        customTabsIntent.launchUrl(this, url.toUri())

        /*customTabsServiceConnection = object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(name: ComponentName, client: CustomTabsClient) {
                client.warmup(0L)
                val session = client.newSession(object : CustomTabsCallback() {
                    override fun onNavigationEvent(navigationEvent: Int, extras: Bundle?) {
                        if (navigationEvent == TAB_HIDDEN) {
                            finish()
                        }
                    }
                })
                val customTabsIntent = CustomTabsIntent.Builder(session)
                    .setDefaultColorSchemeParams(colorSchemeParams)
                    .build()
                customTabsIntent.launchUrl(this@MainActivity, url.toUri())
            }

            override fun onServiceDisconnected(name: ComponentName) { }
        }*/

        CustomTabsClient.bindCustomTabsService(
            this,
            "com.android.chrome", // Change if you want to use another browser.
            customTabsServiceConnection
        )
    }
}