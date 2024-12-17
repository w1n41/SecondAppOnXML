package com.example.secondapponxml

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    private lateinit var questionText: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var nextButton: Button

    private val questions = listOf(
        R.string.question1Text,
        R.string.question2Text,
        R.string.question3Text,
        R.string.question4Text,
        R.string.question5Text
    )

    private val answerArrays = listOf(
        R.array.answers_for_question1,
        R.array.answers_for_question2,
        R.array.answers_for_question3,
        R.array.answers_for_question4,
        R.array.answers_for_question5
    )

    private val correctAnswers = listOf(2, 1, 0, 1, 0)
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        questionText = findViewById(R.id.questionText)
        radioGroup = findViewById(R.id.questionGroup)
        nextButton = findViewById(R.id.nextButton)

        loadQuestions()

        nextButton.setOnClickListener {
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            if(selectedRadioButtonId != -1){
                val selectedIndex = radioGroup.indexOfChild(findViewById(selectedRadioButtonId))

                if(selectedIndex == correctAnswers[currentQuestionIndex]){
                    score++
                }

                currentQuestionIndex++

                if(currentQuestionIndex < questions.size){
                    loadQuestions()
                }else{
                    navigateToResult()
                }
            }
        }
    }

    private fun loadQuestions(){
        questionText.setText(questions[currentQuestionIndex])

        radioGroup.removeAllViews()

        val answers = resources.getStringArray(answerArrays[currentQuestionIndex])

        answers.forEachIndexed{ index, answer ->
            val radioButton = RadioButton(this)
            radioButton.text = answer
            radioButton.id = index
            radioGroup.addView(radioButton)
        }

        radioGroup.clearCheck()

        nextButton.text = if(currentQuestionIndex == questions.size - 1){
            getString(R.string.finish)
        }else{
            getString(R.string.next)
        }
    }

    private fun navigateToResult(){
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE", score)
        intent.putExtra("TOTAL_QUESTIONS", questions.size)
        startActivity(intent)
        finish()
    }
}