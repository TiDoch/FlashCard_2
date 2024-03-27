package com.example.myapplication2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var flashcardQuestion: TextView
    private lateinit var flashcardResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flashcardQuestion = findViewById(R.id.flashcard_question)
        flashcardResponse = findViewById(R.id.flashcard_reponse)

        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardResponse.visibility = View.VISIBLE
        }

        flashcardResponse.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE
            flashcardResponse.visibility = View.INVISIBLE
        }

        val isShowingAnswers = findViewById<ImageView>(R.id.toggle123)
        isShowingAnswers.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data
        if (result.resultCode == Activity.RESULT_OK && data != null) {
            val question = data.getStringExtra("question")
            val answer = data.getStringExtra("answer")

            // Mettre à jour les TextView dans MainActivity avec les nouvelles données
            flashcardQuestion.text = question
            flashcardResponse.text = answer
        } else {
            Log.i("AddCardActivity", "Save operation cancelled or no data returned")
        }
    }
}
