package com.bajaj.cibil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bfl.socketlibrary.BflSdkInit
import com.bfl.socketlibrary.BflSocketSdk
import com.bfl.socketlibrary.ISocketResponse
import org.json.JSONObject

class otp_submit : AppCompatActivity(),ISocketResponse {

    lateinit var otp_submit_button:Button;
    lateinit var otp_entered:EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_submit)

        Log.d("coming into","OTP Submit Activity")
        otp_submit_button = findViewById(R.id.otp_submit_button_id)

        otp_submit_button.setOnClickListener {

            otp_entered = findViewById<EditText?>(R.id.login_otp)
            var otp = otp_entered.text.toString()

            val txnId = intent.getStringExtra("key_txnId")
            val mobile = intent.getStringExtra("key_mobile")

            val my_otp_validate_request = "{ \"mobileNumber\": \"$mobile\", \"channelId\": \"2\", \"deviceId\": \"cibil\", \"txId\": \"$txnId\", \"otp\": \"$otp\"}"

            Log.d("Validate OTP Request","$my_otp_validate_request")

            BflSocketSdk().socketSdk(this,"validateMOOTP","encry_key",my_otp_validate_request);

            Log.d("coming into","Line 40")
        }

    }

    override fun success(data: String, recordId: String) {

        Log.d("coming into","override on success function")

        Log.d("OTP validate response","$data")

        val validateotpresponse = JSONObject(data)

        val mytoken = validateotpresponse.getString("access_token")
        val mymobile = validateotpresponse.getString("mobileNo")

        val my_second_intent = Intent(this,dummy_home_screen::class.java);

        my_second_intent.putExtra("key_token",mytoken)
        my_second_intent.putExtra("key_mobile",mymobile)

        startActivity(my_second_intent)
    }

    override fun failure(data: String, recordId: String) {
        Log.d("OTP Failure response","$data")
    }


}