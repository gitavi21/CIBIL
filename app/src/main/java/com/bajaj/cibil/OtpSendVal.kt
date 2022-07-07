package com.te.js

import android.app.Activity
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.webkit.JsPromptResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
//import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.bfl.socketlibrary.BflSocketSdk
import com.bfl.socketlibrary.ISocketResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject


var txtid=""
var otpvalue=""
var chfe=""
var one=""
val listen : MutableLiveData<String> =  MutableLiveData<String>()

class OtpSendVal(val jsonStr: String, val activity: Activity,val Prog:ProgressBar,val route:String): WebChromeClient() {
    lateinit var phone:String
    lateinit var gResult: JsPromptResult;
    override fun onJsPrompt(
        view: WebView?,
        url: String?,
        message: String?,
        defaultValue: String?,
        result: JsPromptResult?
    ): Boolean {

        Log.println(Log.INFO,"e","Incoming from otpsend ${message} ${url}")
//         result.cancel();
        if (message != null) {
            if (message.startsWith("ask"))
            {
                result?.confirm(route.toString())
            }
            else if (message.startsWith("otpsend")){
                try {
                    if (result != null) {
                        gResult=result
                    }
                    Log.println(Log.INFO,"E","otpsend : " + message)
                    phone=message.split("//")[1].toString()
//                     Log.println(Log.INFO,"E",message.split("//")[1])
                    var login_request = "{ \"mobileNumber\": \"$phone\", \"channelId\": \"2\", \"deviceId\": \"cibil\" }"
//                    BflSocketSdk().socketSdk(this@OtpSendVal, "generateMOOTP", "AQXSFEWWHGUW1K10",login_request)
//                    Prog.visibility=VISIBLE
                    OTPSend(login_request,gResult).socket()


                    GlobalScope.launch {

//                     async{ load1()}


//                   jj

                    }
                    return true
                } catch (e: Exception) {
                    //e.printStackTrace()
                    Log.e("E::EWVA::oJP", e.toString())
                }
            }
            else  if (message.startsWith("otpval")){
                try {
                    if (result != null) {
                        gResult=result
                    }
                    Log.println(Log.INFO,"E","otpval: " + message)
//                    phone=message.split("//")[1].toString()
                    var m = message.split("//")
                    Log.println(Log.INFO,"E","otpval: tx:${m[1]},otp:${m[2]},ph:${m[3]}")

                    val my_otp_validate_request = "{ \"mobileNumber\": \"${m[3]}\", \"channelId\": \"2\", \"deviceId\": \"cibil\", \"txId\": \"${m[1]}\", \"otp\": \"${m[2]}\"}"

                    Log.d("Validate OTP Request","$my_otp_validate_request")
                    Log.println(Log.INFO,"E","otpval: msg:$my_otp_validate_request\"")

                    OTPVal(my_otp_validate_request,gResult).socket()
                    GlobalScope.launch {
//                            delay(1000)



                        }
//                    result?.confirm("" + jsonStr);
                    return true
                } catch (e: Exception) {
                    //e.printStackTrace()
                    Log.e("E::EWVA::oJP", e.toString())
                }
            }
            else  if (message.startsWith("chrread")){
                try {
                    if (result != null) {
                        gResult=result
                    }
                    Log.println(Log.INFO,"E","chrread: " + message)
//                    phone=message.split("//")[1].toString()
                    var m = message.split("//")
                    Log.println(Log.INFO,"E","chrread: tx:${m[1]}")

                    val my_chrread = "{\"mobileNo\": \"${m[1]}\"}"

                    Log.d("Validate OTP Request","$my_chrread")
                    Log.println(Log.INFO,"E","chrread: msg:$my_chrread\"")

                    CHRread(my_chrread,gResult).socket()
                    GlobalScope.launch {
//                            delay(1000)



                    }
//                    result?.confirm("" + jsonStr);
                    return true
                } catch (e: Exception) {
                    //e.printStackTrace()
                    Log.e("E::EWVA::oJP", e.toString())
                }
            }

        }
        return true;

    }


//private suspend fun sampleOne(): Int {
//    println( "sampleOne"+System.currentTimeMillis())
//    delay(2000) // pretend we are doing something useful here
////    prog.visibility = View.INVISIBLE
//    return 10
//}
}


class OTPSend(var login_request:String,var gResult:JsPromptResult):ISocketResponse{
     fun socket(){
         BflSocketSdk().socketSdk(this, "generateMOOTP", "AQXSFEWWHGUW1K10",login_request)

     }
    override fun failure(data: String, recordId: String) {
        Log.println(Log.INFO,"E","otpsend failed")

    }

    override fun success(data: String, recordId: String) {
        GlobalScope.launch {
                Log.println(Log.INFO,"E","otpsend success with $data")
                val genotpresponsejson = JSONObject(data)

                val genotptxnId =genotpresponsejson.getString("txId")
            one= async {  txtid=genotptxnId}.await().toString()

            gResult.confirm("$txtid")
                val genotpmobile = genotpresponsejson.getString("mobileNo")
                val genotpstatuscode = genotpresponsejson.getString("statusCode")
                val genotpdescription = genotpresponsejson.getString("description")
                Log.println(Log.INFO,"E","txtid success with $one")


            Log.println(Log.INFO,"E","receiving response from asyn $data, $one")
        }

    }





}
class OTPVal(var my_otp_validate_request:String,var gResult:JsPromptResult):ISocketResponse{
    fun socket(){
        BflSocketSdk().socketSdk(this,"validateMOOTP","encry_key",my_otp_validate_request);

    }
    override fun failure(data: String, recordId: String) {
        Log.println(Log.INFO,"E","otpval failed with $data")
        otpvalue="fail"
        gResult.confirm("$otpvalue")
        otpvalue=""

    }

    override fun success(data: String, recordId: String) {
        Log.println(Log.INFO,"E","otpval success with $data")
        otpvalue="success"
        gResult.confirm("$otpvalue")
        otpvalue=""

//        val genotpresponsejson = JSONObject(data)
//
//        val genotptxnId =genotpresponsejson.getString("txId")
//        txtid=genotptxnId
//        val genotpmobile = genotpresponsejson.getString("mobileNo")
//        val genotpstatuscode = genotpresponsejson.getString("statusCode")
//        val genotpdescription = genotpresponsejson.getString("description")
//        Log.println(Log.INFO,"E","txtid success with $txtid")
    }





}
class CHRread(var my_chread:String,var gResult:JsPromptResult):ISocketResponse
{
    fun socket(){
        BflSocketSdk().socketSdk(this,"chrread","U2H2J3M5N6P8R9S2",my_chread);

    }
    override fun success(data: String, recordId: String) {
        Log.println(Log.INFO,"E","chrread success with $data")
        chfe="success"

        gResult.confirm("$chfe")
    }
    override fun failure(data: String, recordId: String) {
        Log.println(Log.INFO,"E","chrread failed with $data")
        chfe="fail"

        gResult.confirm("$chfe")
    }



}