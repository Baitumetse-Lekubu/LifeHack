package com.example.lifehack

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Data Class for clean code and low redundancy
data class Flashcard(val statement: String, val isHack: Boolean, val explanation: String)

class MainActivity : AppCompatActivity() {

    private val questions = listOf(
        Flashcard("Use a staple remover to easily add keys to a stiff key ring without breaking your nails.", true, "True! It keeps the ring open safely."),
        Flashcard("Putting a wet smartphone in a bowl of dry rice is the most effective way to dry it.", false, "False! Silica gel or air circulation is actually better."),
        Flashcard("Rub a walnut over scratches in wooden furniture to hide them using the nut's natural oils.", true, "True! The natural oils fill the scratch."),
        Flashcard("Cracking your knuckles causes arthritis in later life.", false, "False! It's just gas bubbles popping."),
        Flashcard("Freeze grapes to use as ice cubes in wine so they chill the drink without watering it down.", true, "True! They act as edible, non-melting ice cubes.")
    )

    private var currentIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateQuestion()

        findViewById<Button>(R.id.btnHack).setOnClickListener { checkAnswer(true) }
        findViewById<Button>(R.id.btnMyth).setOnClickListener { checkAnswer(false) }

        findViewById<Button>(R.id.btnNext).setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                updateQuestion()
            } else {
                // Pass the final score to the Score Screen
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("SCORE", score)
                startActivity(intent)
            }
        }
    }

    private fun updateQuestion() {
        findViewById<TextView>(R.id.txtQuestion).text = questions[currentIndex].statement
        findViewById<TextView>(R.id.txtResult).text = "" // Clear previous feedback
        findViewById<Button>(R.id.btnNext).visibility = View.INVISIBLE
        toggleButtons(true)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correct = questions[currentIndex].isHack
        val resultView = findViewById<TextView>(R.id.txtResult)

        if (userAnswer == correct) {
            score++
            resultView.text = "Correct! ${questions[currentIndex].explanation}"
        } else {
            resultView.text = "Wrong! ${questions[currentIndex].explanation}"
        }

        findViewById<Button>(R.id.btnNext).visibility = View.VISIBLE
        toggleButtons(false)
    }

    private fun toggleButtons(enabled: Boolean) {
        findViewById<Button>(R.id.btnHack).isEnabled = enabled
        findViewById<Button>(R.id.btnMyth).isEnabled = enabled
    }
}