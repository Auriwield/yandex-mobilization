package com.yandex.auriwield.yandextranslate.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.impl.MainThreadImpl;
import com.yandex.auriwield.yandextranslate.domain.executor.impl.ThreadExecutor;
import com.yandex.auriwield.yandextranslate.presentation.presenters.AbstractPresenter;
import com.yandex.auriwield.yandextranslate.presentation.presenters.BasePresenter;
import com.yandex.auriwield.yandextranslate.presentation.ui.BaseView;

import java.security.InvalidParameterException;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public abstract class BaseFragment extends Fragment implements BaseView {
    private static final String EXCEPTION_MSG = "Inheritors of BaseFragment must call initPresenter on onCreate() method";

    private Unbinder unbinder;
    private BasePresenter presenter;

    //TODO: remove
    private AbstractPresenter mock = new AbstractPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance());

    @CallSuper
    @Override
    public void onViewCreated(View view, Bundle savedState) {
        Timber.d(this.getClass() + "onViewCreated");
        super.onViewCreated(view, savedState);
        unbinder = ButterKnife.bind(this, view);
        if (presenter == null) {
            presenter = mock;
            Timber.w("%s: %s", this.getClass(), EXCEPTION_MSG);
        }
        presenter.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null)
            presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        Timber.d(this.getClass() + "onDestroyView");
        super.onDestroyView();
        unbinder.unbind();
        presenter.destroy();
    }

    public boolean isReady() {
        return presenter != null && presenter != mock;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && presenter != null)
            presenter.userVisible();
    }

    protected void initPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(@StringRes int id) {
        Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(@StringRes int id, int length) {
        Toast.makeText(getContext(), id, length).show();
    }
}
