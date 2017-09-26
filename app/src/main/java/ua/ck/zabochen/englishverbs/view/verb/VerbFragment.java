package ua.ck.zabochen.englishverbs.view.verb;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.ck.zabochen.englishverbs.MainApp;
import ua.ck.zabochen.englishverbs.R;
import ua.ck.zabochen.englishverbs.model.VerbList;
import ua.ck.zabochen.englishverbs.model.realm.Verb;
import ua.ck.zabochen.englishverbs.utils.listener.RecyclerViewItemTouchListener;
import ua.ck.zabochen.englishverbs.view.base.BaseFragment;

public class VerbFragment extends BaseFragment implements VerbContract.View {

    private static final String TAG = VerbFragment.class.getSimpleName();

    @Inject VerbPresenter mVerbPresenter;
    @Inject VerbList mVerbList;

    @BindView(R.id.fragmentVerb_recyclerView) RecyclerView mVerbRecyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainApp.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verb, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Verb RecyclerView Layout
        mVerbRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        // Verb RecyclerView Listener
        mVerbRecyclerView.addOnItemTouchListener(new RecyclerViewItemTouchListener(
                getContext(),
                mVerbRecyclerView,
                new RecyclerViewItemTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        mVerbPresenter.adapterOnClick(getActivity(), view, position);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }
        ));

        // Presenter
        mVerbPresenter.attachView(this);
        mVerbPresenter.viewIsReady();
    }

    @Override
    public void setAdapter(ArrayList<Verb> verbs) {
        mVerbRecyclerView.setAdapter(new VerbAdapter(verbs));
    }

}
