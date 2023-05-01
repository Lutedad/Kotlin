package com.firstapp.wake_up


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MomSubActivity : AppCompatActivity() {

    private lateinit var dDay: TextView
    private lateinit var content: TextView
    private lateinit var btnToTo: ImageButton
    private lateinit var giftboxmom: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mom_sub)

        dDay = findViewById(R.id.dDay)
        content = findViewById(R.id.commentMomDday)
        btnToTo = findViewById(R.id.btnTotoMom)
        giftboxmom = findViewById(R.id.giftboxmom)

        dDayCal()
    }

    private fun dDayCal(){
        val sampleDate = "2023-04-29 00:00:00" //5-18 엄마생일
        val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = sf.parse(sampleDate)

        val today = Calendar.getInstance(TimeZone.getDefault(),Locale.getDefault())
        today.apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }


        if (date != null) {
            val calcuDate = TimeUnit.MILLISECONDS.toDays(today.time.time - date.time).toInt()
            if (calcuDate == 0) {
                dDay.setText(R.string.d_day)
                comment(calcuDate)
            } else if (calcuDate > 0) {
                dDay.text = getString(R.string.d_day_plus, calcuDate)
                comment(calcuDate)
            } else {
                dDay.text = getString(R.string.d_day_minus, calcuDate)
            }
        } else {
            dDay.text = getString(R.string.unknown)
        }
    }

    private fun comment(date: Int) {
        if (date == 0){
            content.text = getString(R.string.happy_birthday)
            giftboxmom.visibility = VISIBLE
            giftboxmom.setOnClickListener{
                val intent = Intent(this, MomFinalActivity::class.java)
                startActivity(intent)
            }
        }else if (date > 0){
            content.text = getString(R.string.happy_late_birthday)
            btnToTo.visibility = VISIBLE
            btnToTo.setOnClickListener{
                val intent = Intent(this, MomFinalActivity::class.java)
                startActivity(intent)
            }
        }
    }


}