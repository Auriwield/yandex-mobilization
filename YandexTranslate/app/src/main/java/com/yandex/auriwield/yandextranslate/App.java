package com.yandex.auriwield.yandextranslate;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.leakcanary.LeakCanary;

import java.util.Locale;

import dagger.Module;
import timber.log.Timber;

/**
 * Created by auriw on 23.03.2017.
 */

public class App extends Application {

    public final static String UI_CODE = Locale.getDefault().getLanguage();

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        initLeakCanary();
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                // Adds the line number to the tag
                @Override protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        }
    }
}
