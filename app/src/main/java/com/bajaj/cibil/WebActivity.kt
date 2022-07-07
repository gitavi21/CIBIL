package com.bajaj.cibil

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.webkit.JsPromptResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.te.js.OtpSendVal
import com.te.js.otpvalue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var jsonStr:String = "d2e";

class WebActivity : AppCompatActivity(){
    lateinit var view: WebView
    lateinit var prog:ProgressBar
    override  fun onCreate(savedInstanceState: Bundle?) {
        var route:String=intent.getStringExtra("route").toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        view=findViewById(R.id.hsWebview)


        prog= findViewById(R.id.hsprogressBar)
        prog.visibility= VISIBLE

        GlobalScope.launch {
            delay(2000)
        prog.visibility=INVISIBLE
//        view = WebView(this)
            // Default text zoom size
//            Log.println(Log.INFO,"e","Completed in $time ms")

        }
      load(route)



    }
    fun load( route: String){
        recweb(route)

        view.settings.javaScriptEnabled = true
        WebView.setWebContentsDebuggingEnabled(true);
        view.settings.allowFileAccess = true
        view.settings.allowContentAccess = true
        view.settings.allowUniversalAccessFromFileURLs = true
        view.setWebChromeClient(
            OtpSendVal(jsonStr, this@WebActivity, findViewById(R.id.hsprogressBar),route)

        )
//            view.webChromeClient=WebChromeClient().apply {  }
        view.settings.javaScriptEnabled = true

        view.settings.textZoom=100
        view.loadUrl("file:///android_asset/index.html")

        Log.println(Log.INFO,"e","RUNNING PWA")
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.getAction() === KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (view.canGoBack()) {
                        view.goBack()
                    } else {
                        finish()
                    }
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }

}
class recweb(var  mes:String):WebChromeClient(){
    override fun onJsPrompt(
        view: WebView?,
        url: String?,
        message: String?,
        defaultValue: String?,
        result: JsPromptResult?
    ): Boolean {
        result?.confirm(mes)
        return true
    }
}