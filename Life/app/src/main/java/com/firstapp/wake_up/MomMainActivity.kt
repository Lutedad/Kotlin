package com.firstapp.wake_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MomMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mom_main);

        findViewById<ImageButton>(R.id.btn_mom_main).setOnClickListener {
            val intent = Intent(this, MomSubActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn_main_menu3).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}