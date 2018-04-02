package ua.ck.zabochen.englishverbs.helper.speech

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech

import java.util.*

class SpeechHelper(context: Context) {

    private val mContext: Context = context
    private lateinit var mTextToSpeech: TextToSpeech

    fun speech(text: String) {
        mTextToSpeech = TextToSpeech(mContext,
                { status ->
                    if (status == TextToSpeech.SUCCESS) {
                        mTextToSpeech.language = Locale.US
                        mTextToSpeech.setPitch(0.5F)
                        mTextToSpeech.setSpeechRate(0.5F)

                        // Speak
                        speak(text)
                    }
                })
    }

    private fun speak(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

}