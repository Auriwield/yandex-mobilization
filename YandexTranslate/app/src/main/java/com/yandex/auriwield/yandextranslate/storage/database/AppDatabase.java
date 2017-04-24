package com.yandex.auriwield.yandextranslate.storage.database;

/**
 * Created by GZaripov1 on 28.03.2017.
 */

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    static final String NAME = "yandex_translate_app_db";
    static final int VERSION = 1;

}
