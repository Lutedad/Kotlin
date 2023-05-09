package com.lutedad.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextClock
import android.widget.TextView
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity() {
    private lateinit var MainTime: TextView
    private lateinit var BtnImage: ImageButton
    private lateinit var clock: TextClock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        BtnImage = findViewById(R.id.btn_main)
        clock = findViewById(R.id.main_time)


        BtnImage.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        clock.text = ("It's..."+clock.text)
    }



    override fun onBackPressed() {
        val intent = Intent(this, LogoActivity::class.java)
        startActivity(intent)

    }
}