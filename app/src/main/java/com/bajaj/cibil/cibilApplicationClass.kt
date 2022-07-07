package com.bajaj.cibil

import android.app.Application
import android.provider.Settings
import android.util.Log
import com.bfl.socketlibrary.BflSdkInit
import com.bfl.socketlibrary.BflSocketSdk
import com.bfl.socketlibrary.ISocketResponse

public class cibilApplicationClass : Application(), ISocketResponse{

    val cibilApplicationClass: cibilApplicationClass? = null //Replicated from Offers

    fun getInstance(): cibilApplicationClass? {             //Replicated from Offers
        return cibilApplicationClass
    }

    val BFL_WEBSOCKET_ENDPOINT = "wss://sauat.bajajfinserv.in/wsc"
    val CHANNEL_ID = "2"
    val SOURCE = "local"
    var AUTHORISATION = "Basic clpVU3kwSFNKZlA5TUtrOl9jUGtLb3VwLXMtZGlSQlZHN1ZKZ040Vmh1QQ=="
    var SOCKET_OP = "5PjAvswgKktK"
    var SOCKET_LG = "82HLTJQ9QNJW4MXP"
    val API_VERSION = "v1"
    val APP_VERSION_CODE = "android_v13"
    val SOCKET_BROADCAST_CODE = "com.bfl.socketlibrary.SESSION_EXPIRE"

    override fun onCreate(){
        super.onCreate()

        Log.d("coming into","Cibil Application class")



//        BflSdkInit.setAPIEndPoint(BFL_WEBSOCKET_ENDPOINT)//Replicated from Offers
//        BflSdkInit.setAuthBearer(AUTHORISATION)//Replicated from Offers
//        BflSdkInit.setSource("3in1")//Replicated from Offers
//        BflSdkInit.setappVersion(APP_VERSION_CODE)//Replicated from Offers
//        BflSdkInit.setApiVersion("v1")//Replicated from Offers
//        BflSocketSdk().initSocketSdk(this, this)//Replicated from Offers

//        var context = applicationContext;
//
//
//        BflSdkInit.setappVersion(APP_VERSION_CODE)
//        BflSdkInit.setAPIEndPoint(BFL_WEBSOCKET_ENDPOINT)
//        BflSdkInit.setAuthBearer(AUTHORISATION)
//        BflSdkInit.setSource(SOURCE)
////        BflSdkInit.setApiVersion(API_VERSION)
////        BflSdkInit.setApiVersion("v1")
//        BflSdkInit.setChannel(CHANNEL_ID)
//        BflSdkInit.setAuthOP(SOCKET_OP)
//        Log.e("Socket SOCKET_OP", SOCKET_OP)
//        BflSdkInit.setAuthLG(SOCKET_LG)
////        BflSocketSdk.appContext = context
////        BflSocketSdk.appContext = this.applicationContext
//        Log.e("Socket SOCKET_LG", SOCKET_LG)
//        BflSdkInit.setDeviceIdHeader(
//            Settings.Secure.getString(
//                contentResolver,
//                Settings.Secure.ANDROID_ID
//            )
//        )

        BflSdkInit.setappVersion(APP_VERSION_CODE)
        BflSdkInit.setAPIEndPoint(BFL_WEBSOCKET_ENDPOINT)
        BflSdkInit.setAuthBearer(AUTHORISATION)
        BflSdkInit.setSource(SOURCE)
//        BflSdkInit.setApiVersion(API_VERSION)
        BflSdkInit.setChannel(CHANNEL_ID)
        BflSdkInit.setAuthOP(SOCKET_OP)
        Log.e("Socket SOCKET_OP", SOCKET_OP)
        BflSdkInit.setAuthLG(SOCKET_LG)
        BflSocketSdk.appContext = applicationContext
        Log.e("Socket SOCKET_LG", SOCKET_LG)
        BflSdkInit.setDeviceIdHeader(
            Settings.Secure.getString(
                contentResolver,
                Settings.Secure.ANDROID_ID
            )
        )

        BflSocketSdk().initSocketSdk(this, this)

    }

    fun test(){
        Log.d("coming into","test function")
    }

    override fun failure(data: String, recordId: String) {
        Log.e("Socket Fail","Failure")
    }

    override fun success(data: String, recordId: String) {
        Log.e("Socket Success","Success")
    }

}