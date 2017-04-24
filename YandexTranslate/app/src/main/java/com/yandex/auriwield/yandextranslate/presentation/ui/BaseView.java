package com.yandex.auriwield.yandextranslate.presentation.ui;

import android.support.annotation.StringRes;

/**
 * @author g.zaripov
 */

public interface BaseView {
    void showError(@StringRes int id);
    void showError(@StringRes int id, int length);
}
