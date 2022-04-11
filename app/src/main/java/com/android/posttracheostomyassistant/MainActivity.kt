package com.android.posttracheostomyassistant

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech (this, this)
        findViewById<MaterialButton>(R.id.btnTalk).setOnClickListener { talk() }

    }

    private fun talk () {
        var message: String = findViewById<TextView>(R.id.etMessage).text.toString()

        if (message.isEmpty()) {
            findViewById<TextView>(R.id.tvInitStatus).text = "Introducir un texto !"
            message = "Por favor, introducir un texto !"
        }
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            findViewById<TextView>(R.id.tvInitStatus).text = getString(R.string.tts_active)
            tts!!.language = Locale ("ES")

        }else {
            findViewById<TextView>(R.id.tvInitStatus).text = getString(R.string.tts_no_active)
        }
    }
    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}