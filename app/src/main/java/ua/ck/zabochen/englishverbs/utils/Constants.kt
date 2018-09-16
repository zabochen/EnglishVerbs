package ua.ck.zabochen.englishverbs.utils

class Constants {
    companion object {

        // Verbs File
        @JvmStatic
        val VERBS_JSON_FILE_PATH: String = "englishVerbs.json"

        // Database
        @JvmStatic
        val DATABASE_NAME: String = "englishVerbs.db"

        @JvmStatic
        val DATABASE_NOTIFICATION_ID: Int = 1

        // Activity for result
        @JvmStatic
        val AFR_VERB_FULL_ACTIVITY_DESTROY: Int = 100

        @JvmStatic
        val AFR_INTENT_KEY_VERB_FULL_ACTIVITY_BOOKMARK_STATE: String = "afr_intentKey_verbFullActivity_bookmark_state"

        // Intents
        @JvmStatic
        val INTENT_SELECTED_VERB_ID: String = "intent_selected_verb_id"

        // Notifications
        @JvmStatic
        val NOTIFICATION_ID: Int = 0

        @JvmStatic
        val NOTIFICATION_CHANNEL_ID: String = "English verbs"
    }
}