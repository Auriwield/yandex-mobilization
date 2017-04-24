package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.favorites;

import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base.FavoritesTranslationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author g.zaripov
 */
public class FavoriteAdapter extends FavoritesTranslationAdapter {

    public void removeItem(int position) {
        if (position < 0 || position >= translationList.size()) return;
        translationList.remove(position);
        notifyItemRemoved(position);
    }

    public void updateData(List<Translation> translationList) {
        this.translationList = new ArrayList<>(translationList);
        filteredList.clear();
        filteredList.addAll(translationList);
        notifyDataSetChanged();
    }


}
