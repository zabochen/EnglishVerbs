package ua.ck.zabochen.englishverbs.database;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;
import ua.ck.zabochen.englishverbs.model.realm.Verb;
import ua.ck.zabochen.englishverbs.mvp.interactor.Callback;

public class RealmHelper {

    private static final String TAG = RealmHelper.class.getSimpleName();

    private Context mContext;

    public RealmHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void inflateDatabase(Callback.DatabaseCallback callback) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
            try {
                realm.createOrUpdateAllFromJson(Verb.class, mContext.getAssets().open("englishVerbs.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, () -> {
            // Inflate success
            callback.onDatabaseSuccess();
            Log.i(TAG, "inflateDatabase: True");
        }, error -> {
            // Inflate error
            callback.onDatabaseError(error);
            Log.i(TAG, "inflateDatabase: False");
        });

    }

    public ArrayList<Verb> getVerbList() {

        Realm realmInstance = Realm.getDefaultInstance();

        RealmResults<Verb> realmResults = realmInstance
                .where(Verb.class)
                .findAll();

        ArrayList<Verb> verbList = new ArrayList<>();
        verbList.addAll(realmResults);


        // Close RealmInstance
        if (!realmInstance.isClosed()) {
            realmInstance.close();
            Log.i(TAG, "getVerbList: Get VerbList & Close Realm");
        }

        return verbList;
    }

    public Verb getRandomVerb() {

        Realm realmInstance = Realm.getDefaultInstance();

        RealmResults<Verb> realmResults = realmInstance
                .where(Verb.class)
                .findAll();

        Random random = new Random(System.nanoTime());
        int randomNumber = random.nextInt(realmResults.size());
        Verb randomVerb = realmResults.get(randomNumber);

        // Close RealmInstance
        if (!realmInstance.isClosed()) {
            realmInstance.close();
            Log.i(TAG, "getVerbList: Get VerbList & Close Realm");
        }

        return randomVerb;
    }

}
