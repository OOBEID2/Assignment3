package com.example.quickfly

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import android.content.Intent
import android.widget.Toast
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {
    private val Channel_ID = "channel_id_example_01"
    private val notificationId = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinnerDes: Spinner = findViewById(R.id.spDest)
        val spinnerBag: Spinner = findViewById(R.id.spBag)
        val spinnerIns: Spinner = findViewById(R.id.spIns)
        var flagDes: String
        var flagDes2: Double = 0.0
        var flagBag: String
        var flagBag2: Double = 0.0
        var flagIns: String
        //Second activity part
        val editConfrimation: EditText = findViewById(R.id.editConfrim)
        val btnConfirmation: Button = findViewById(R.id.btConfirm)




        createNotificationChannel()

        btnConfirmation.setOnClickListener{
            val message: String = editConfrimation.text.toString()

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("key", message)
            startActivity(intent)

            sendNotification()
        }


        val Ins: Spinner = findViewById(R.id.spIns)
        var Insurance = arrayOf("Yes", "No")
        spinnerIns.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Insurance)
        spinnerBag.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flagIns = Insurance.get(p2);
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Empty
            }
        }

        val Bag: Spinner = findViewById(R.id.spBag)
        var Baggage = arrayOf("1", "2", "3", "4", "5")

        val Bprices: Spinner = findViewById(R.id.spBag)
        var Bprice = arrayOf(1,2,3,4,5)

        spinnerBag.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Baggage)
        spinnerBag.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flagBag = Baggage.get(p2);
                flagBag2 = Bprice.get(p2).toDouble();
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Empty
            }
        }

        val Des: Spinner = findViewById(R.id.spDest)
        var Dest = arrayOf("USA - California"/*800*/, "USA - New York"/*900*/, "USA - Texas",/*850*/
            "Italy - Rome"/*700*/, "Italy - Milan"/*725*/, "Spain - Barcelona"/*800*/,
            "Spain - Madrid"/*650*/, "Germany - Munich" /*580*/, "Germany - Dortmund"/*550*/,
            "France - Paris"/*630*/, "England - London"/*800*/, "England - Manchester"/*700*/
            ,"China - Hong Kong"/*450*/, "Turkey - Istanbul"/*400*/, "Turkey - Ankara"/*380*/,
            "Egypt - Cairo"/*200*/, "UAE - Dubai"/*420*/, "UAE - Abu Dhabi"/*400*/,
            "Qatar - Doha"/*425*/) // 19 countries

        val prices:Spinner = findViewById(R.id.spDest)
        var price = arrayOf(800, 900, 850, 700, 725, 800, 650, 580, 550, 630, 800, 700, 450, 400,
        380, 200, 420, 400, 425)
        Des.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Dest)
        spinnerDes.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flagDes = Dest.get(p2);
                flagDes2 = price.get(p2).toDouble();
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Empty
            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean{
        val inflater = menuInflater
        inflater.inflate(R.menu.fly_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.subitemC1->Toast.makeText(this, "Selected: Platinum Class", Toast.
            LENGTH_SHORT).show();
            R.id.subitemC2->Toast.makeText(this, "Selected: First Class", Toast.
            LENGTH_SHORT).show();
            R.id.subitemC3->Toast.makeText(this, "Selected: Business Class", Toast.
            LENGTH_SHORT).show();
            R.id.subitemC4->Toast.makeText(this, "Selected: Premium Economy Class",
                Toast.LENGTH_SHORT).show();
            R.id.subitemC5->Toast.makeText(this, "Selected: Economy Class", Toast.
            LENGTH_SHORT).show();
            R.id.subitemR1->Toast.makeText(this, "Car type: Mercedes C63", Toast.
            LENGTH_SHORT).show();
            R.id.subitemR2->Toast.makeText(this, "Car type: Kia K5", Toast.
            LENGTH_SHORT).show();
            R.id.subitemR3->Toast.makeText(this, "Car type: Ford Focus", Toast.
            LENGTH_SHORT).show();
            R.id.subitemR4->Toast.makeText(this, "Car type: Honda Civic", Toast.
            LENGTH_SHORT).show();
            R.id.subitemR5->Toast.makeText(this, "Car type: Toyota Camry", Toast.
            LENGTH_SHORT).show();
        }
        return true;
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptonText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(Channel_ID,name,importance).apply {
                description = descriptonText;
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }

    }

    private fun sendNotification(){
        val intent = Intent(this, MainActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


        val builder = NotificationCompat.Builder(this, Channel_ID)
            .setSmallIcon(R.drawable.ic_showing_toast)
            .setContentTitle("Quick Fly - Ticket Confirmation")
            .setContentText("Example Description")
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigTextStyle().bigText("Your ticket has been confirmed. Tick Number: A-987254. If you would like to " +
                    "edit your ticket you have 48 hours. Just press on this notification to edit it."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())

        }
    }





}