package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.history;

import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base.FavoritesTranslationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author g.zaripov
 */
public class HistoryAdapter extends FavoritesTranslationAdapter {

    public void updateData(Translation data, int position) {
        translationList.set(position, data);
        notifyItemChanged(position);
    }

    public void updateData(List<Translation> translationList) {
        this.translationList = new ArrayList<>(translationList);
        filteredList.clear();
        filteredList.addAll(translationList);
        notifyDataSetChanged();
    }
}
