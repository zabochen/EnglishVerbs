package ua.ck.zabochen.englishverbs.helper.speech

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import java.util.*

class SpeechHelper(private val context: Context) {

    private lateinit var textToSpeech: TextToSpeech

    fun speak(text: String) {
        textToSpeech = TextToSpeech(context) { state ->
            if (state == TextToSpeech.SUCCESS) {
                textToSpeech.language = Locale.US
                textToSpeech.setPitch(0.7F)
                textToSpeech.setSpeechRate(0.5F)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                } else {
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null)
                }
            }
        }
        //textToSpeech.shutdown()
    }
}