package com.lutedad.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import java.text.SimpleDateFormat



class MainActivity : AppCompatActivity() {
    private lateinit var MainTime: TextView
    private lateinit var btn_image: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainTime = findViewById(R.id.main_time)
        btn_image = findViewById(R.id.btn_main)


        btn_image.setOnClickListener{
            val intent = Intent(this, LogoActivity::class.java)
            startActivity(intent)
        }

        time()
    }

    private fun time() {
        val currentTime : Long = System.currentTimeMillis() // ms로 반환
        val dataFormat4 = SimpleDateFormat("hh:mm")
        val formatted = dataFormat4.format(currentTime)
        MainTime.text = "It's...$formatted"
    }
}