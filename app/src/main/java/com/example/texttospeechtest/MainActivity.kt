package com.example.texttospeechtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.Locale
class MainActivity : AppCompatActivity() {
    private lateinit var textToSpeech:TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val editText = findViewById<EditText>(R.id.editText)
        val textToSpeechBtn = findViewById<Button>(R.id.textToSpeechBtn)

        textToSpeech = TextToSpeech(this){ status->
            if(status == TextToSpeech.SUCCESS){
                val result = textToSpeech.setLanguage(Locale.getDefault())
                if(result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Toast.makeText(this, "language is not supported", Toast.LENGTH_LONG).show()
                }
            }
        }


        textToSpeechBtn.setOnClickListener{
            Log.d("MainActivity", "Button clicked")
            if(editText.text.toString().trim().isNotEmpty()) {
                textToSpeech.speak(
                    editText.text.toString().trim(),
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                )
            } else {
                Toast.makeText(this, "Required text", Toast.LENGTH_LONG).show()
            }
        }
    }


}