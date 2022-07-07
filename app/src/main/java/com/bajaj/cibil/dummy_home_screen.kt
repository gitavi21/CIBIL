package com.bajaj.cibil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bfl.socketlibrary.BflSocketSdk
import com.bfl.socketlibrary.ISocketResponse
import com.google.gson.JsonObject
import org.json.JSONObject

class dummy_home_screen : AppCompatActivity(),ISocketResponse {

    lateinit var my_get_cibil_score_button : ImageView;
    lateinit var cst:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy_home_screen)

        my_get_cibil_score_button = findViewById(R.id.get_cibil_score_button_id)

        cst=findViewById(R.id.cstage)
        my_get_cibil_score_button.setOnClickListener {

            Log.d("coming into","onclick Listener of get cibil score button")

            val mobile = intent.getStringExtra("key_mobile")

            var chr_read_request = "{ \"mobileNo\": \"$mobile\"}"
            Log.println(Log.INFO,"e","chrread request $chr_read_request")

            BflSocketSdk().socketSdk(this,"chrread","U2H2J3M5N6P8R9S2",chr_read_request)

            Log.d("coming into","Line 31")
        }
    }

    override fun success(data: String, recordId: String) {

        Log.d("Coming into","On success Function of CHR Read")
        Log.d("CHR Read Response ","$data")



        val chrreadresponse = JSONObject(data)

//        chrread success with {"description":"NO CHR DATA FOUND","statusCode":"7001","latestChrDate":null,"mobileNo":null,"eventCode":null,"cibilFetchDate":null,"cibilVisitedFlag":false,"payFlag":null,"fName":null,"lName":null,"pan":null,"dob":null,"pinCode":null,"emailId":null,"empType":null,"monthlySalary":null,"cibilScore":null,"paymentDetail":null}
        val myeventcode = chrreadresponse?.getString("eventCode")
        val desc = chrreadresponse?.getString("description")
        val cibilVisitedFlag=chrreadresponse?.getString("cibilVisitedFlag")
        val payflag=chrreadresponse?.getString("payFlag")
        cst.setText(myeventcode.toString())
        Log.println(Log.INFO,"e","chrread success with $data $myeventcode $desc $cibilVisitedFlag $payflag")
        val my_first_intent = Intent(this,MainActivity2::class.java)


        my_first_intent.putExtra("myeventcode",myeventcode)
        my_first_intent.putExtra("description",desc)
        my_first_intent.putExtra("cibilVisitedFlag",cibilVisitedFlag)
        my_first_intent.putExtra("payflag",payflag)

        Log.d("coming into ","Line 74")

        startActivity(my_first_intent);

        Log.d("event code is ","$myeventcode")

    }
    override fun failure(data: String, recordId: String) {
        Log.d("Coming into","On Failure of CHR Read Function")
    }
}