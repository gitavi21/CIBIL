package com.bajaj.cibil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bfl.socketlibrary.BflSocketSdk
import com.bfl.socketlibrary.ISocketResponse
import org.json.JSONObject
import kotlin.math.log

class MainActivity : AppCompatActivity(),ISocketResponse {

    lateinit var user_login_mobile_number:EditText
    lateinit var generate_otp_button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        BflSocketSdk().initSocketSdk(this,this)
        generate_otp_button
        generate_otp_button = findViewById(R.id.generate_otp_button_id)
        Log.println(Log.INFO,"e","id is ${R.id.generate_otp_button_id}")
//        BflSocketSdk().socketSdk(this, "generateMOOTP", "AQXSFEWWHGUW1K10","{\n" +
//                "  \"mobileNumber\": \"7477796999\",\n" +
//                "  \"channelId\": \"1\",\n" +
//                "  \"deviceId\": \"cibil_testing_device\"\n" +
//                "}")

        Log.d("coming into","Line 32")

        generate_otp_button.setOnClickListener {

            user_login_mobile_number = findViewById<EditText?>(R.id.login_mobile_number)
            var mobile = user_login_mobile_number.text.toString();

            var login_request = "{ \"mobileNumber\": \"$mobile\", \"channelId\": \"2\", \"deviceId\": \"cibil\" }"

            Toast.makeText(this,"$login_request",Toast.LENGTH_LONG).show()
            Log.d("My Login Request is ", "$login_request")

            BflSocketSdk().socketSdk(this, "generateMOOTP", "AQXSFEWWHGUW1K10",login_request)
        }
    }
    override fun failure(data: String, recordId: String) {
        Toast.makeText(this,"Generate OTP Failed",Toast.LENGTH_LONG);
        println("Failure Response: " + data)
    }

    override fun success(data: String, recordId: String) {
        println("Success Response: " + data)

        val genotpresponsejson =JSONObject(data)

        val genotptxnId =genotpresponsejson.getString("txId")
        val genotpmobile = genotpresponsejson.getString("mobileNo")
        val genotpstatuscode = genotpresponsejson.getString("statusCode")
        val genotpdescription = genotpresponsejson.getString("description")

        Log.d("Generate OTP","is $genotpdescription")

        Log.d("coming into ","Line 66")

        val my_first_intent = Intent(this,otp_submit::class.java)

        my_first_intent.putExtra("key_mobile",genotpmobile);
        my_first_intent.putExtra("key_txnId",genotptxnId);
        my_first_intent.putExtra("key_statuscode",genotpstatuscode);

        Log.d("coming into ","Line 74")

        startActivity(my_first_intent);
    }

}