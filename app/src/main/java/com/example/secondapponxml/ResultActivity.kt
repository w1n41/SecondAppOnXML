package com.example.secondapponxml

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val resultText: TextView = findViewById(R.id.resultText)
        val tryAgainButton: Button = findViewById(R.id.tryAgainButton)
        val exitButton: Button = findViewById(R.id.exitButton)

        val score = intent.getIntExtra("SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)

        resultText.text = getString(R.string.result_text, score, totalQuestions)

        tryAgainButton.setOnClickListener {
            val intent = Intent(this, GreetingActivity::class.java)
            startActivity(intent)
            finish()
        }

        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}