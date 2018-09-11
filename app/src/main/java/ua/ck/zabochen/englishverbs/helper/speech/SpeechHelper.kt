package ua.ck.zabochen.englishverbs.helper.speech

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import java.util.*

class SpeechHelper(private val context: Context) {

    private var textToSpeech: TextToSpeech? = null

    fun speak(text: String) {
        textToSpeech = TextToSpeech(context) { state ->
            if (state == TextToSpeech.SUCCESS) {
                textToSpeech?.let {
                    it.language = Locale.US
                    it.setPitch(0.7F)
                    it.setSpeechRate(0.5F)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        it.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                    } else {
                        it.speak(text, TextToSpeech.QUEUE_FLUSH, null)
                    }
                }
            }
        }
    }

    fun clear() {
        if (textToSpeech != null) {
            textToSpeech!!.shutdown()
        }
    }
}