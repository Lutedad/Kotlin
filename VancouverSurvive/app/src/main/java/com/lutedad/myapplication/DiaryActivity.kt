package com.lutedad.myapplication

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Space
import androidx.core.content.res.ResourcesCompat

class DiaryActivity : AppCompatActivity() {

    private lateinit var backspace: ImageButton
    private lateinit var diaryContainer: ScrollView
    private lateinit var list: ImageButton
    private lateinit var writeDiary: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)


        backspace = findViewById(R.id.backspace_diary)
        list = findViewById(R.id.diary_list)
        diaryContainer = findViewById(R.id.diary_container)
        writeDiary = findViewById(R.id.write_diary)


        backspace.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


        // 리스트 버튼 클릭시 리스트 나오게 하기.

        list.setOnClickListener {

        }

        writeDiary.setOnClickListener {
            val intent = Intent(this, DiaryWriteActivity::class.java)
            startActivity(intent)
        }



        diarymaker()
    }


    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    private fun diarymaker() {
        val numDiaries = 50 // 일기 개수
        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.hehe)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL

        for (i in 0 until numDiaries) {
            val buttonLayout = LinearLayout(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                150
            )
            layoutParams.setMargins(0, 30, 0, 0)
            buttonLayout.layoutParams = layoutParams
            buttonLayout.orientation = LinearLayout.HORIZONTAL
            buttonLayout.weightSum = 1f

            val space1 = Space(this)
            val space1Params = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                0.11f
            )
            space1.layoutParams = space1Params

            val dateButton = Button(this)
            val dateButtonParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                0.11f
            )
            dateButton.layoutParams = dateButtonParams
            dateButton.text = "Jan\n0${i}"
            dateButton.setBackgroundResource(R.drawable.diary_inside)
            dateButton.gravity = Gravity.CENTER
            dateButton.typeface = typeface
            dateButton.textSize = 17F

            val space2 = Space(this)
            val space2Params = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                0.04f
            )
            space2.layoutParams = space2Params

            val contentButton = Button(this)
            val contentButtonParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                0.63f
            )
            contentButton.layoutParams = contentButtonParams
            contentButton.text = "이것은 테스트입니다! 똥똥땅땅꾸꽝낑히"
            contentButton.setBackgroundResource(R.drawable.diary_inside)
            contentButton.gravity = Gravity.CENTER
            contentButton.typeface = typeface
            contentButton.textSize = 20F
            contentButton.setSingleLine()
            contentButton.ellipsize = TextUtils.TruncateAt.END

            buttonLayout.addView(space1)
            buttonLayout.addView(dateButton)
            buttonLayout.addView(space2)
            buttonLayout.addView(contentButton)
            linearLayout.addView(buttonLayout)

            // Space 추가
            val space3 = Space(this)
            val space3Params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                10
            )
            space3.layoutParams = space3Params
            linearLayout.addView(space3)
        }

        diaryContainer.addView(linearLayout)
    }


}