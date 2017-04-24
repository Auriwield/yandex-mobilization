package com.yandex.auriwield.yandextranslate.network.rx;



import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


/**
 * Created by auriw on 28.03.2017.
 */

public class RxUtils {

    private static Consumer<Throwable> mOnError = new Consumer<Throwable>() {
        @Override
        public void accept(@NonNull Throwable throwable) throws Exception {
            Timber.d(RxUtils.class.getSimpleName(), throwable);
        }
    };

    public static <T> Disposable asyncRequest(Observable<T> observable, Consumer<? super T> action) {
        return asyncRequest(observable, action, mOnError);
    }

    public static <T> Disposable asyncRequest(Observable<T> observable, Consumer<? super T> action, Consumer<Throwable> mOnError) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action, mOnError);
    }
}
