package com.example.quickfly

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_main)

        val bundle: Bundle? = intent.extras

        bundle?.let{
            val msg = bundle.getString("key")
            //Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
            val v : TextView = findViewById(R.id.txtEnd)
            val w: TextView = findViewById(R.id.txtMove)
            w.text = msg

        }



    }
}