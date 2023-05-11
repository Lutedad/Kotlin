package com.lutedad.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DiaryWriteActivity : AppCompatActivity() {

    private lateinit var dateDiary: TextView
    private lateinit var dateContent: EditText
    private lateinit var dateTitle: EditText
    private lateinit var submit: ImageButton
    private lateinit var backspace: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_write)

        submit = findViewById(R.id.submit_diary)
        dateDiary = findViewById(R.id.date_diary)
        dateContent = findViewById(R.id.contentDiary)
        dateTitle = findViewById(R.id.title_diary)
        backspace = findViewById(R.id.backspace_diary)

        val dateIs = date()
        val directory = "${this.filesDir.path}/com.lutedad.myapplication"

        // 파일 읽고 창에 띄우기
        val (title, content) = readTextFile("$directory/$dateIs")
        dateTitle.text = Editable.Factory.getInstance().newEditable(title)
        dateContent.text = Editable.Factory.getInstance().newEditable(content)

        backspace.setOnClickListener {
            showLeaveConfirmationDialog()
        }

        submit.setOnClickListener {
            val titleDiary = dateTitle.text.toString()
            val contentDiary = dateContent.text.toString()

            showSaveConfirmationDialog(directory, dateIs, titleDiary, contentDiary)
        }
    }

    override fun onBackPressed() {
        showLeaveConfirmationDialog()
    }

    private fun showSaveConfirmationDialog(directory: String, dateIs: String, titleDiary: String, contentDiary: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Save")
        alertDialogBuilder.setMessage("Do you want to save it?")
        alertDialogBuilder.setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
            writeTextFile(directory, dateIs, titleDiary, contentDiary)
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
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog: DialogInterface, _: Int ->
        }
        alertDialogBuilder.show()
    }


    private fun date(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH)
        val formattedDate = currentDate.format(formatter)
        dateDiary.text = formattedDate
        return formattedDate
    }

    private fun writeTextFile(directory: String, filename: String, title: String, content: String) {
        val file = File(directory, filename)
        file.parentFile?.mkdirs()

        val writer = FileWriter(file)
        writer.use {
            it.write("$title\n") // 제목을 파일에 쓰기
            it.write(content) // 내용을 파일에 쓰기
        }
    }

    private fun readTextFile(path: String): Pair<String, String> {
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
}

