package com.yandex.auriwield.yandextranslate;

import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;

/**
 * Created by GZaripov1 on 29.03.2017.
 */

public class TestMainThread implements MainThread {

    @Override
    public void post(Runnable runnable) {
        // tests can run on this thread, no need to invoke other threads
        runnable.run();
    }
}