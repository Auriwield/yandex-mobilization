package com.yandex.auriwield.yandextranslate.presentation.ui.activity;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.yandex.auriwield.yandextranslate.presentation.ui.BaseView;

import butterknife.ButterKnife;

/**
 * @author g.zaripov
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void showError(@StringRes int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(@StringRes int id, int length) {
        Toast.makeText(this, id, length).show();
    }
}
