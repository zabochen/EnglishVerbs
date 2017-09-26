package ua.ck.zabochen.englishverbs.view.verb;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import javax.inject.Inject;

import ua.ck.zabochen.englishverbs.MainApp;
import ua.ck.zabochen.englishverbs.R;
import ua.ck.zabochen.englishverbs.database.RealmHelper;
import ua.ck.zabochen.englishverbs.mvp.interactor.Callback;
import ua.ck.zabochen.englishverbs.mvp.presenter.BasePresenter;
import ua.ck.zabochen.englishverbs.view.verbfull.VerbFullActivity;

public class VerbPresenter extends BasePresenter<VerbContract.View> implements VerbContract.Presenter {

    private static final String TAG = VerbPresenter.class.getSimpleName();

    @Inject
    VerbFragment mVerbFragment;
    @Inject
    RealmHelper mRealmHelper;

    public VerbPresenter() {
        MainApp.getAppComponent().inject(this);
    }

    @Override
    public void viewIsReady() {

        mRealmHelper.inflateDatabase(new Callback.DatabaseCallback() {
            @Override
            public void onDatabaseSuccess() {
                mVerbFragment.setAdapter(mRealmHelper.getVerbList());
            }

            @Override
            public void onDatabaseError(Throwable error) {
                error.printStackTrace();
            }
        });

    }

    @Override
    public void adapterOnClick(Activity activity, View view, int position) {
        ImageView imageView = view.findViewById(R.id.adapterVerb_image);
        String imageTransitionName = activity.getString(R.string.transition_image);
        Intent intent = new Intent(activity, VerbFullActivity.class)
                .putExtra("position", position);

        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                imageView,
                imageTransitionName
        );

        ActivityCompat.startActivity(
                activity,
                intent,
                activityOptionsCompat.toBundle());
    }

}
