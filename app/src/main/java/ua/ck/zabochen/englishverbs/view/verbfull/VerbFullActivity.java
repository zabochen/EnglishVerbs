package ua.ck.zabochen.englishverbs.view.verbfull;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.ck.zabochen.englishverbs.MainApp;
import ua.ck.zabochen.englishverbs.R;
import ua.ck.zabochen.englishverbs.model.realm.Verb;
import ua.ck.zabochen.englishverbs.utils.Tools;
import ua.ck.zabochen.englishverbs.view.base.BaseActivity;

public class VerbFullActivity extends BaseActivity implements VerbFullContract.View {

    private static final String TAG = VerbFullActivity.class.getSimpleName();

    @Inject
    VerbFullPresenter mVerbFullPresenter;

    // Toolbar
    @BindView(R.id.activityVerbFull_toolbar) Toolbar mToolbar;

    // Header Image
    @BindView(R.id.activityVerbFull_imageView_verbImage) ImageView mImage;

    // Verb Translation
    @BindView(R.id.activityVerbFull_textView_verbTranslation) TextView mVerbTransalation;

    // Verb
    @BindView(R.id.activityVerbFull_textView_verb) TextView mVerb;
    @BindView(R.id.activityVerbFull_textView_verbTranscription) TextView mVerbTranscription;
    @BindView(R.id.activityVerbFull_imageView_verbPlay) ImageView mVerbPlay;

    // VerbPastSimple
    @BindView(R.id.activityVerbFull_texView_verbPastSimple) TextView mVerbPastSimple;
    @BindView(R.id.activityVerbFull_textView_verbPastSimpleTranscription) TextView mVerbPastSimpleTranscription;
    @BindView(R.id.activityVerbFull_imageView_verbPastSimplePlay) ImageView mVerbPastSimplePlay;

    // VerbPastParticiple
    @BindView(R.id.activityVerbFull_texView_verbPastParticiple) TextView mVerbPastParticiple;
    @BindView(R.id.activityVerbFull_textView_verbPastParticipleTranscription) TextView mVerbPastParticipleTranscription;
    @BindView(R.id.activityVerbFull_imageView_verbPastParticiplePlay) ImageView mVerbPastParticiplePlay;

    private int mVerbPosition;
    private TextToSpeech mTextToSpeech;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Injection
        MainApp.getAppComponent().inject(this);

        // Verb Position
        mVerbPosition = getIntent().getIntExtra("position", 0);

        // UI
        setTextToSpeech();
        setUi();
    }

    private void setTextToSpeech() {
        mTextToSpeech = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                mTextToSpeech.setLanguage(Locale.CANADA);
            }
        });
    }

    private void setUi() {
        // Layout
        setContentView(R.layout.activity_verb_full);
        ButterKnife.bind(this);

        // Toolbar
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolbar_title));

        // Presenter
        mVerbFullPresenter.attachView(this);
        mVerbFullPresenter.viewIsReady();
        // Pass selected item
        mVerbFullPresenter.verbPosition(mVerbPosition);
    }

    @Override
    public void updateUi(Verb verb) {

        // Rounded image
        if (verb.getVerbImage() != null) {
            mImage.setImageDrawable(Tools.getRoundedImageFromAssets(this, verb.getVerbImage()));
        } else {
            mImage.setImageDrawable(Tools.getRoundedImageFromResources(this, R.drawable.no_image));
        }

        // Verb Translation
        mVerbTransalation.setText(verb.getVerbTranslation());

        // Verb
        mVerb.setText(verb.getVerb());
        mVerbTranscription.setText(verb.getVerbTranscription());
        mVerbPlay.setOnClickListener(view -> mTextToSpeech.speak(
                verb.getVerb(),
                TextToSpeech.QUEUE_FLUSH,
                null)
        );

        // VerbPastSimple
        mVerbPastSimple.setText(verb.getVerbPastSimple());
        mVerbPastSimpleTranscription.setText(verb.getVerbPastSimpleTranscription());
        mVerbPastSimplePlay.setOnClickListener(view -> mTextToSpeech.speak(
                verb.getVerbPastSimple(),
                TextToSpeech.QUEUE_FLUSH,
                null)
        );

        // VerbPastParticiple
        mVerbPastParticiple.setText(verb.getVerbPastParticiple());
        mVerbPastParticipleTranscription.setText(verb.getVerbPastParticipleTranscription());
        mVerbPastParticiplePlay.setOnClickListener(view -> mTextToSpeech.speak(
                verb.getVerbPastParticiple(),
                TextToSpeech.QUEUE_FLUSH,
                null)
        );

    }

    @OnClick(R.id.activityVerbFull_button_notification)
    public void buttonNotificationClick() {
        mVerbFullPresenter.createNotification(mVerbPosition);
    }

    @Override
    protected void onPause() {
        if (mTextToSpeech != null) {
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
        }

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVerbFullPresenter.detachView();
    }

}
