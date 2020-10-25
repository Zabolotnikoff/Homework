package com.example.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_A.setOnClickListener {
            startActivity(Intent(this, ActivityB::class.java))
        }

        button_Start_MyService.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }

        button_Stop_MyService.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        println("onBackPressed")
        this.finishAffinity()
    }


}