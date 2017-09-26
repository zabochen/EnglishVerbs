package ua.ck.zabochen.englishverbs.view.verb;

import android.app.Activity;

import java.util.ArrayList;

import ua.ck.zabochen.englishverbs.model.realm.Verb;
import ua.ck.zabochen.englishverbs.mvp.presenter.MvpPresenter;
import ua.ck.zabochen.englishverbs.mvp.view.MvpView;

public class VerbContract {

    public interface View extends MvpView {
        void setAdapter(ArrayList<Verb> verbs);
    }

    public interface Presenter extends MvpPresenter<View> {
        void adapterOnClick(Activity activity, android.view.View view, int position);
    }

}
