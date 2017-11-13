package ua.ck.zabochen.englishverbs.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ua.ck.zabochen.englishverbs.view.main.MainPresenter;
import ua.ck.zabochen.englishverbs.view.verbfull.VerbFullPresenter;
import ua.ck.zabochen.englishverbs.view.verblist.VerbListPresenter;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainPresenter mainPresenter);
    void inject(VerbListPresenter verbListPresenter);
    void inject(VerbFullPresenter verbFullPresenter);

}
