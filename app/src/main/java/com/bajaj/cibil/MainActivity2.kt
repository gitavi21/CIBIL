package com.bajaj.cibil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.te.js.OtpSendVal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.DriverManager.println
import kotlin.system.measureTimeMillis
var f:Boolean=false
val listen : MutableLiveData<String> =  MutableLiveData<String>()
class MainActivity2 : AppCompatActivity() {

    lateinit var b1:Button
    lateinit var view:WebView
    lateinit var b2:Button
    lateinit var b3:Button

    lateinit var t:TextView
    private  val handler = Handler()

    lateinit var prog:ProgressBar
    override  fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        b1=findViewById(R.id.button)

        b2=findViewById(R.id.button2)
        b3=findViewById(R.id.button3)
        t=findViewById(R.id.textView)
        val myeventcode = intent.getStringExtra("myeventcode")
        val desc = intent.getStringExtra("description")
        val cibilVisitedFlag = intent.getStringExtra("cibilVisitedFlag").toBoolean()
        val payflag= intent.getStringExtra("payflag").toBoolean()

        prog=findViewById(R.id.progressBar2)

        prog.visibility = View.INVISIBLE
        listen.observe(this@MainActivity2, Observer {
            if (listen.value=="n")
            {
                Log.println(Log.INFO,"got","got that m as ${t.text}")

            }

            //Do something with the changed value -> it

        })
        view = WebView(this)
        var jsonStr:String = "d2e";
        b2.setOnClickListener{
            prog.visibility = View.VISIBLE
        GlobalScope.launch {
//            async{ waitin()}

            val time = measureTimeMillis {

                val one = sampleOne()
                val two = sampleTwo()

                Log.println(Log.INFO,"e","The answer is ${one + two}")
            }
            Log.println(Log.INFO,"e","Completed in $time ms")

            }

        }

        b1.setOnClickListener{
            val my_first_intent = Intent(this,WebActivity::class.java)
            if (cibilVisitedFlag==false)
            {
                my_first_intent.putExtra("route","first")

            }
            else if(cibilVisitedFlag == true && payflag == false) {
                if(myeventcode == "CIBIL01")
                {
//                    Show him the fresh journey form screen with all the details populated in the fields
//                    the journey is same as Fresh cibil Journey
                    my_first_intent.putExtra("route","first")

                }

            }

            startActivity(my_first_intent);
        }


    }
private suspend fun sampleOne(): Int {
    println( "sampleOne"+System.currentTimeMillis())
    delay(2000) // pretend we are doing something useful here
//    prog.visibility = View.INVISIBLE
    return 10
}

private suspend fun sampleTwo(): Int {
    println( "sampleTwo"+System.currentTimeMillis())
    delay(1000L)
    this@MainActivity2.runOnUiThread(java.lang.Runnable {
        f=true
        //Initilize with a value

        t.setText("got the value")
        listen.setValue("n")
    })
// pretend we are doing something useful here, too
    return 10
}
private fun waitin(){



}
}