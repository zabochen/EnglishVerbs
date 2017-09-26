package ua.ck.zabochen.englishverbs.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ua.ck.zabochen.englishverbs.view.main.MainActivity;
import ua.ck.zabochen.englishverbs.view.setting.SettingFragment;
import ua.ck.zabochen.englishverbs.view.setting.SettingPresenter;
import ua.ck.zabochen.englishverbs.view.verb.VerbAdapter;
import ua.ck.zabochen.englishverbs.view.verb.VerbFragment;
import ua.ck.zabochen.englishverbs.view.verb.VerbPresenter;
import ua.ck.zabochen.englishverbs.view.verbfull.VerbFullActivity;
import ua.ck.zabochen.englishverbs.view.verbfull.VerbFullPresenter;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    // Main Activity
    void inject(MainActivity mainActivity);

    // Verb Fragment
    void inject(VerbFragment verbFragment);

    // Verb Presenter
    void inject(VerbPresenter verbPresenter);

    // Verb Adapter
    void inject(VerbAdapter verbAdapter);

    // VerbFull Activity
    void inject(VerbFullActivity verbFullActivity);

    // VerbFull Presenter
    void inject(VerbFullPresenter verbFullPresenter);

    // Setting Fragment
    void inject(SettingFragment settingFragment);

    // Setting Presenter
    void inject(SettingPresenter settingPresenter);
}
