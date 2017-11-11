package ua.ck.zabochen.englishverbs.utils

class Constants {

    companion object {

        // Verbs File
        @JvmStatic val VERBS_JSON_FILE_PATH: String = "englishVerbs.json"

        // Realm Database
        @JvmStatic val REALM_DATABASE_NAME: String = "englishVerbs.realm"
        @JvmStatic val REALM_DATABASE_VERSION: Long = 0

        // Intents
        @JvmStatic val INTENT_VERB_SELECTED_POSITION: String = "intent_verb_selected_position"

    }

}