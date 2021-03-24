package org.d3ifcool.catat

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.web_view.*


class WebView_Acitivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)


       webView.webViewClient = WebViewClient()

        webView.apply {
                loadUrl("https://console.firebase.google.com/")
                settings.javaScriptEnabled = true
            }




    }

    override fun onBackPressed() {
        if(webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }
}