package com.example.bemyguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var answer = 0
    var isGameOver = false
    var numOfAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startOver()
    }

    fun generateAnswer() {

        answer = Random.nextInt(1, 25)
    }

    fun startOver() {

        isGameOver = false
        generateAnswer()
        numOfAttempts = 0

        answer = Random.nextInt(1, 25)

        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = "??"

        val submitButton = findViewById<Button>(R.id.buttonSubmit)
        submitButton.isEnabled = true

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Guess 1..25"

        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        editTextGuess.text.clear()

    }

    fun btnStartOverTapped(view: View) {
        generateAnswer()
    }

    fun btnSubmitTapped(view: View) {

        val guess = getUsersGuess() ?: -999

        val textView = findViewById<TextView>(R.id.textView)
        if (guess !in 1..25) {
            textView.text = "Guess must be 1 to 25"
            return

        }

        var message = ""
        numOfAttempts++

        if (guess == answer) {
            message = "Correct! Guess(es): $numOfAttempts"
            isGameOver = true

            val answerTextView = findViewById<TextView>(R.id.answer)
            answerTextView.text = answer.toString()

            val submitButton = findViewById<Button>(R.id.buttonSubmit)
            submitButton.isEnabled = false

        }
        else {
            message = if (guess < answer) "Too low!" else "Too high!"
        }

        textView.text = message
    }

    fun getUsersGuess() : Int? {
        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        val userGuess = editTextGuess.text.toString()

        val guessAsInt = userGuess.toInt()

        try {

            var guessAsInt = userGuess.toInt()
        }
        catch (e: Exception) {
            return null
        }

        return guessAsInt
    }
}