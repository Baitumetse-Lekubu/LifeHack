package com.example.lifehack

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val finalScore = intent.getIntExtra("SCORE", 0)
        val txtScore = findViewById<TextView>(R.id.txtScore)
        val txtFeedback = findViewById<TextView>(R.id.txtPersonalFeedback)

        txtScore.text = "Total Score: $finalScore / 5"

        // Scoring logic[cite: 1]
        txtFeedback.text = if (finalScore >= 3) "Master Hacker!" else "Stay Safe Online!"

        findViewById<Button>(R.id.btnReview).setOnClickListener {
            showReview()
        }
    }

    private fun showReview() {
        val review = """
            1. Staple Remover: Hack (True)
            2. Rice for Phone: Myth (False)
            3. Walnut for Wood: Hack (True)
            4. Knuckle Cracking: Myth (False)
            5. Frozen Grapes: Hack (True)
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Correct Answers")
            .setMessage(review)
            .setPositiveButton("OK", null)
            .show()
    }
}