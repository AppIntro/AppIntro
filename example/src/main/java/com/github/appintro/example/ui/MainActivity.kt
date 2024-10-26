package com.github.appintro.example.ui

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.appintro.example.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable StrictMode
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
        setContentView(R.layout.activity_main)

       // val viewManager = LinearLayoutManager(this)
      //  val viewAdapter = IntroAdapter(defaultEntries)

        //  val recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view).apply {
          //  recyclerView.setHasFixedSize(true)
          //  layoutManager = viewManager
           // adapter = viewAdapter

           setContentView(R.layout.activity_main)
           val viewManager = LinearLayoutManager(this)
           val viewAdapter = IntroAdapter(defaultEntries)

           val recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
           recyclerView.setHasFixedSize(true)
           recyclerView.layoutManager = viewManager
           recyclerView.adapter = viewAdapter

       }
    }
