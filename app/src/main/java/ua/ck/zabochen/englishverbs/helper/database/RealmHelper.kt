package ua.ck.zabochen.englishverbs.helper.database

import android.content.Context
import io.realm.Realm
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.callback.CallbackEvent
import ua.ck.zabochen.englishverbs.model.realm.Verb
import ua.ck.zabochen.englishverbs.utils.Constants

class RealmHelper(private val applicationContext: Context) : AnkoLogger {

    fun inflateDatabase(subscriber: CallbackEvent.DatabaseCallback) {
        Realm.getDefaultInstance().executeTransactionAsync(
                { realm ->
                    realm.createOrUpdateAllFromJson(
                            Verb::class.java,
                            applicationContext.assets.open(Constants.VERBS_JSON_FILE_PATH)
                    )
                },
                {
                    subscriber.onComplete()
                },
                { error ->
                    subscriber.onError(error)
                }
        )
    }

    fun getVerbList(): ArrayList<Verb> {

        val realmInstance = Realm.getDefaultInstance()

        // Get Verb List
        val verbList: ArrayList<Verb> = ArrayList()
        verbList.addAll(realmInstance.where(Verb::class.java).findAll())

        // Close Realm
        if (!realmInstance.isClosed) realmInstance.close()

        return verbList
    }

    fun getVerb(verbPosition: Int): Verb? {

        val realmInstance = Realm.getDefaultInstance()

        // Get Verb
        val realmResult = realmInstance.where(Verb::class.java).findAll()

        // Close Realm
        if (!realmInstance.isClosed) realmInstance.close()

        return realmResult[verbPosition]
    }

//    public Verb getRandomVerb() {
//
//        Realm realmInstance = Realm.getDefaultInstance();
//
//        RealmResults<Verb> realmResults = realmInstance
//                .where(Verb.class)
//                .findAll();
//
//        Random random = new Random(System.nanoTime());
//        int randomNumber = random.nextInt(realmResults.size());
//        Verb randomVerb = realmResults.get(randomNumber);
//
//        // Close RealmInstance
//        if (!realmInstance.isClosed()) {
//            realmInstance.close();
//            Log.i(TAG, "getVerbList: Get VerbList & Close Realm");
//        }
//
//        return randomVerb;
//    }

}