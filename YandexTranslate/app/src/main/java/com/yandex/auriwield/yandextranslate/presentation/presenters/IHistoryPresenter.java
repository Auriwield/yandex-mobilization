package com.yandex.auriwield.yandextranslate.presentation.presenters;

import android.view.View;

import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.presentation.ui.BaseView;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base.FavoritesTranslationAdapter;

import java.util.List;

/**
 * @author g.zaripov
 */
public interface IHistoryPresenter extends BasePresenter, FavoritesTranslationAdapter.FavoritesTranslationAdapterInteraction, View.OnClickListener {

    void getAllTranslations();

    @Override
    void onClick(android.view.View v);

    interface View extends BaseView {
        void updateData(List<Translation> data);
        void updateItem(Translation data, int position);
        void clear();
    }
}
