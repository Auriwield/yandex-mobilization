package com.yandex.auriwield.yandextranslate.presentation.presenters;

import android.view.View;

import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.presentation.ui.BaseView;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base.FavoritesTranslationAdapter;

import java.util.List;

/**
 * @author g.zaripov
 */
public interface IFavoritesPresenter extends BasePresenter, FavoritesTranslationAdapter.FavoritesTranslationAdapterInteraction, View.OnClickListener {

    void getFavoriteTranslations();

    @Override
    void onClick(android.view.View v);

    interface View extends BaseView {
        void onDataUpdate(List<Translation> data);
        void removeItem(int position);
        void clearData();
        void checkList();
    }
}
