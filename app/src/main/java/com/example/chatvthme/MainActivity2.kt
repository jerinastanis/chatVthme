package com.example.chatvthme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val up:Animation=AnimationUtils.loadAnimation(applicationContext,R.anim.up)
        val down:Animation=AnimationUtils.loadAnimation(applicationContext,R.anim.down)
        var c= Runnable {
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }
        val text:TextView=findViewById(R.id.appname)
        val text1:TextView=findViewById(R.id.appname2)
        val text2:TextView=findViewById(R.id.appname3)
        text.animation=down
        text1.animation=down
        text2.animation=down
        var x=android.os.Handler(Looper.getMainLooper())
        x.postDelayed(c,3000)
    }
}