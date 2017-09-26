package ua.ck.zabochen.englishverbs.mvp.interactor;

public interface Callback {

    interface DatabaseCallback {
        void onDatabaseSuccess();
        void onDatabaseError(Throwable error);
    }

}
