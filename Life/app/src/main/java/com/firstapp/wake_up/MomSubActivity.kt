package com.firstapp.wake_up


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MomSubActivity : AppCompatActivity() {

    private lateinit var dDay: TextView
    private lateinit var content: TextView
    private lateinit var btnToTo: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mom_sub)

        dDay = findViewById(R.id.dDay)
        content = findViewById(R.id.commentMomDday)
        btnToTo = findViewById(R.id.btnTotoMom)

        dDayCal()
        comment(dDayCal())
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun dDayCal(): Int {
        val sampleDate = "2023-04-29 00:00:00" // 05-18
        val sf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
        val date = sf.parse(sampleDate)


        val today = Calendar.getInstance()
        today.apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val calcuDate = ((today.time.time - date.time) / (60 * 60 * 24 * 1000))
        if (calcuDate.toInt() == 0){
            dDay.text = "D-DAY"
        }else if (calcuDate.toInt() > 0){
            val d = calcuDate.toString()
            dDay.text = "D+$d"}
        else{
            val d = calcuDate.toString()
            dDay.text = "D$d"}
        return calcuDate.toInt()
    }

    @SuppressLint("SetTextI18n")
    private fun comment(date: Int) {
        if (date == 0){
            content.text = "생일 축하합니다!\n밑의 상자를 클릭해보세요!"
        }else if (date > 0){
            content.text = "생일이 지났쇼\n다시 확인하려면 토토를\n클릭하쇼"
            btnToTo.visibility = VISIBLE
            btnToTo.setOnClickListener(){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }


}