package ua.ck.zabochen.englishverbs.utils

import ua.ck.zabochen.englishverbs.R

class Constants {
    companion object {
        // Verbs File
        @JvmStatic
        val VERBS_JSON_FILE_PATH: String = "englishVerbs.json"

        // Realm Database
        @JvmStatic
        val REALM_DATABASE_NAME: String = "englishVerbs.realm"

        @JvmStatic
        val REALM_DATABASE_VERSION: Long = 0

        // Intents
        @JvmStatic
        val INTENT_VERB_SELECTED_POSITION: String = "intent_verb_selected_position"

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