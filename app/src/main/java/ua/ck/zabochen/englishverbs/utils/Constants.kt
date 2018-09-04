package ua.ck.zabochen.englishverbs.utils

import ua.ck.zabochen.englishverbs.R

class Constants {
    companion object {
        // Activity for result
        @JvmStatic
        val ACTIVITY_FOR_RESULT_ACTIVITY_DESTROY: Int = 1000

        // Verbs File
        @JvmStatic
        val VERBS_JSON_FILE_PATH: String = "englishVerbs.json"

        // Database
        @JvmStatic
        val DATABASE_NAME: String = "englishVerbs.db"

        // Realm Database
        @JvmStatic
        val REALM_DATABASE_NAME: String = "englishVerbs.realm"

        @JvmStatic
        val REALM_DATABASE_VERSION: Long = 0

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

// Bottom Navigation View Item
enum class BottomNavigationViewItem(val id: Int) {
    HOME(R.id.menuBottomNavigationView_item_home),
    BOOKMARKS(R.id.menuBottomNavigationView_item_bookmarks),
    NOTIFICATION(R.id.menuBottomNavigationView_item_notifications)
}