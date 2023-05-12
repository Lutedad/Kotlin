package com.lutedad.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class DiaryReWriteActivity : AppCompatActivity() {

    private lateinit var dateDiary: Button
    private lateinit var diaryContent: EditText
    private lateinit var diaryTitle: EditText
    private lateinit var submit: ImageButton
    private lateinit var backspace: ImageButton
    private lateinit var deleteDiary: ImageButton
    private lateinit var selectDiaryScroll: LinearLayout
    private lateinit var selectDiaryScrollParent: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_re_write)

        submit = findViewById(R.id.submit_diary)
        dateDiary = findViewById(R.id.date_diary)
        diaryContent = findViewById(R.id.contentDiary)
        diaryTitle = findViewById(R.id.title_diary)
        backspace = findViewById(R.id.backspace_diary)
        deleteDiary = findViewById(R.id.delete_diary)
        selectDiaryScroll = findViewById(R.id.select_diary_list)
        selectDiaryScrollParent = findViewById(R.id.select_diary_list_parent)

        // DiaryReWriteActivity에서 인텐트 데이터 사용
        val intent = intent
        val dateD = try {
            intent.getStringExtra("date")
        } catch (ecp: ExceptionInInitializerError) {
            Mysingleton.date()
        }

        dateDiary.text = dateD

        val directory = "${this.filesDir.path}/com.lutedad.myapplication"

        // 파일 읽고 창에 띄우기
        val (titleDiary, content) = Mysingleton.readTextFile("$directory/$dateD")
        diaryTitle.text = Editable.Factory.getInstance().newEditable(titleDiary)
        diaryContent.text = Editable.Factory.getInstance().newEditable(content)

        backspace.setOnClickListener {
            showLeaveConfirmationDialog()
        }

        submit.setOnClickListener {
            val titleDiarySubmit = diaryTitle.text.toString()
            val contentDiary = diaryContent.text.toString()

            if (dateD != null) {
                showSaveConfirmationDialog(directory, dateD, titleDiarySubmit, contentDiary)
            }
        }

        deleteDiary.setOnClickListener {
            val dateOfDeletingDiary = dateDiary.text.toString()
            showDeleteConfirmationDialog(directory, dateOfDeletingDiary)
        }

        dateDiary.setOnClickListener{
            selectDiaryScrollParent.visibility = View.VISIBLE
            diaryListScrollFun()
        }

    }

    override fun onBackPressed() {
        showLeaveConfirmationDialog()
    }


    object Mysingleton {
        fun readTextFile(path: String): Pair<String, String> {
            try {
                val file = File(path)
                if (file.exists()) {
                    val lines = file.readLines()
                    val title = lines.firstOrNull() ?: ""
                    val content = lines.drop(1).joinToString("\n")

                    return Pair(title, content)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return Pair("", "")

        }


        fun writeTextFile(directory: String, filename: String, title: String, content: String) {
            val file = File(directory, filename)
            file.parentFile?.mkdirs()

            val writer = FileWriter(file)
            writer.use {
                it.write("$title\n") // 제목을 파일에 쓰기
                it.write(content) // 내용을 파일에 쓰기
            }
        }


        fun deleteFile(filePath: String): Boolean {
            val file = File(filePath)
            return file.delete()
        }


        fun date(): String {
            val currentDate = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH)
            return currentDate.format(formatter)
        }

    }


    private fun showSaveConfirmationDialog(
        directory: String,
        dateIs: String,
        titleDiary: String,
        contentDiary: String
    ) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Save")
        alertDialogBuilder.setMessage("Do you want to save it?")
        alertDialogBuilder.setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
            Mysingleton.writeTextFile(directory, dateIs, titleDiary, contentDiary)
            dialog.dismiss()
            val intent = Intent(this, DiaryActivity::class.java)
            startActivity(intent)
        }
        alertDialogBuilder.setNegativeButton("No") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
        alertDialogBuilder.show()
    }

    private fun showLeaveConfirmationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Leave")
        alertDialogBuilder.setMessage("Do you want to leave?\nChanges will not be reflected.")
        alertDialogBuilder.setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
            val intent = Intent(this, DiaryActivity::class.java)
            startActivity(intent)
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("No") { _: DialogInterface, _: Int ->
        }
        alertDialogBuilder.show()
    }

    private fun showDeleteConfirmationDialog(directory: String, dateOfDeletingDiary: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Delete")
        alertDialogBuilder.setMessage("Do you want to delete?")
        alertDialogBuilder.setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
            Mysingleton.deleteFile("$directory/$dateOfDeletingDiary")
            val intent = Intent(this, DiaryActivity::class.java)
            startActivity(intent)
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("No") { _: DialogInterface, _: Int ->
        }
        alertDialogBuilder.show()
    }

    private fun diaryListScrollFun() {
        var dateD: String
        val directory = File("${this.filesDir.path}/com.lutedad.myapplication")  // 특정 위치의 디렉토리 경로
        val fileNames = directory.list()  // 디렉토리 내의 파일 이름 배열

        val typeface: Typeface? = ResourcesCompat.getFont(this, R.font.hehe)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL

        // overlay view 생성
        val overlayView = View(this)
        overlayView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        overlayView.setBackgroundColor(Color.TRANSPARENT)
        overlayView.isClickable = true
        overlayView.isFocusable = true

        // overlay view 클릭 이벤트 처리
        overlayView.setOnClickListener {
            // 클릭 이벤트 무시
        }

        // overlay view 포커스 이벤트 처리
        overlayView.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // 포커스 이벤트 무시
            }
        }

        selectDiaryScrollParent.addView(overlayView)  // overlay view 추가

        //파일을 읽어온 뒤, 각각에 상응하는 버튼들 생성
        for (filename in fileNames!!) {
            dateD = filename

            val buttonLayout = LinearLayout(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 10, 0, 0)
            buttonLayout.layoutParams = layoutParams
            buttonLayout.orientation = LinearLayout.VERTICAL
            buttonLayout.gravity = Gravity.CENTER_HORIZONTAL

            val dateView = TextView(this)
            val dateButtonParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                100
            )
            dateView.layoutParams = dateButtonParams
            dateView.text = dateD
            dateView.setBackgroundResource(R.drawable.button_solid1)
            dateView.setTextColor(Color.BLUE)
            dateView.gravity = Gravity.CENTER
            dateView.typeface = typeface
            dateView.textSize = 20F

            buttonLayout.addView(dateView)
            linearLayout.addView(buttonLayout)

            dateView.setOnClickListener {
                val intent = Intent(this, DiaryReWriteActivity::class.java)
                intent.putExtra("date", dateD)
                startActivity(intent)
            }
        }

        selectDiaryScroll.addView(linearLayout)
    }



}