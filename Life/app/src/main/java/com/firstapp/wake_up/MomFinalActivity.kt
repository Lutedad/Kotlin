package com.firstapp.wake_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.ProgressBar






class MomFinalActivity : AppCompatActivity() {
    private lateinit var progress: ProgressBar
    private lateinit var letter : com.airbnb.lottie.LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mom_final)

        progress = findViewById(R.id.progressMom)
        letter = findViewById(R.id.lottie_letter)



    }

    private fun btnlove(){


        progresscal((1..3).random())
    }

    private fun progresscal(num : Int){

        if (progress.progress == 100){
            lotletter()
        }else{
            progress.progress += num
        }

    }

    private fun lotletter(){
        letter.visibility = VISIBLE
        letter.playAnimation()
    }
}