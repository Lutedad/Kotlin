package com.lutedad.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class DiaryWrite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_write)


        val packageName = "test"
        val directory = "/data/data/$packageName"
        val filename = "folderName"
        writeTextFile(directory,filename,"test")


    }


    private fun writeTextFile(directory: String, filename: String, content: String) {
        /* directory가 존재하는지 검사하고 없으면 생성 */
        val dir = File(directory)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val writer = FileWriter("$directory/$filename")  // FileWriter 생성
        Log.d("FileUtil","write dir=${"$directory/$filename"}")
        val buffer = BufferedWriter(writer)  // buffer에 담아서 속도 향상

        buffer.write(content)  // buffer로 내용 쓰고
        buffer.close()  // buffer 닫기
    }
}