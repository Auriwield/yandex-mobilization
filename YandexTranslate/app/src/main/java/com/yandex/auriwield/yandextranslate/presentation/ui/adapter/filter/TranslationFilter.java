package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.filter;

import android.widget.Filter;

import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base.FavoritesTranslationAdapter;
import com.yandex.auriwield.yandextranslate.storage.model.translate.TranslationModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author g.zaripov
 */
public class TranslationFilter extends Filter {

    private FavoritesTranslationAdapter adapter;
    private List<Translation> originalList;
    private List<Translation> filteredList;

    public TranslationFilter(FavoritesTranslationAdapter adapter, List<Translation> translationList) {
        this.adapter = adapter;
        this.originalList = translationList;
        filteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        final FilterResults results = new FilterResults();

        if (constraint.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            final String filterPattern = constraint.toString().toLowerCase().trim();

            for (final Translation translation : originalList) {
                if (translation.getText().toLowerCase().contains(filterPattern)
                        || translation.getTranslation().toLowerCase().contains(filterPattern)) {
                    filteredList.add(translation);
                }
            }
        }
        results.values = filteredList;
        results.count = filteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.updateFilter((ArrayList<Translation>) results.values);
    }
}
