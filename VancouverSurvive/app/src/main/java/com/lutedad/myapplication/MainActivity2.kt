package com.lutedad.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity2 : AppCompatActivity() {

    private lateinit var diary: ImageButton
    private lateinit var backspace: ImageButton
    private lateinit var mail: ImageButton
    private lateinit var insta: ImageButton
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        diary = findViewById(R.id.btn_diary)
        backspace = findViewById(R.id.backspace2)
        mail = findViewById(R.id.mail)
        insta = findViewById(R.id.insta)

        backspace.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        Glide.with(this).load(R.raw.diary).into(findViewById(R.id.btn_diary))

        mail.setOnClickListener{
            val email = Intent(Intent.ACTION_SEND)
            email.type = "plain/text"
            val address = arrayOf("choi.junu.june0127@gmail.com")
            email.putExtra(Intent.EXTRA_EMAIL, address)
            email.putExtra(Intent.EXTRA_SUBJECT, "Report Error")
            email.putExtra(Intent.EXTRA_TEXT, "//Write Here//")
            startActivity(email)
        }

        insta.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/june0127_6/"))
            startActivity(intent)
        }


    }


    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}