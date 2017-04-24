package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.filter.TranslationFilter;
import com.yandex.auriwield.yandextranslate.storage.model.translate.TranslationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author g.zaripov
 */
public abstract class FavoritesTranslationAdapter
        extends RecyclerView.Adapter<FavoritesTranslationAdapter.TranslationViewHolder>
        implements Filterable {
    protected List<Translation> translationList;
    protected FavoritesTranslationAdapterInteraction mListener;
    private TranslationFilter translationFilter;
    protected List<Translation> filteredList;

    public FavoritesTranslationAdapter() {
        translationList = new ArrayList<>();
        filteredList = new ArrayList<>();
    }

    public FavoritesTranslationAdapter(List<Translation> translationList) {
        this.translationList = translationList != null ? translationList : new ArrayList<Translation>();
        filteredList = new ArrayList<>();
    }

    public void clear() {
        translationList.clear();
        notifyDataSetChanged();
    }

    public interface FavoritesTranslationAdapterInteraction {
        void onFavoriteIconClick(Translation translation, int position);

        void onItemClick(Translation translation, int position);
    }

    public List<Translation> getTranslationList() {
        return translationList;
    }

    public FavoritesTranslationAdapterInteraction getListener() {
        return mListener;
    }

    public FavoritesTranslationAdapter setListener(FavoritesTranslationAdapterInteraction mListener) {
        this.mListener = mListener;
        return this;
    }

    @Override
    public TranslationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.translation_list_item, parent, false);
        return new TranslationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TranslationViewHolder holder, int position) {
        final Translation translation = filteredList.get(position);
        holder.favoriteIcon.setImageResource(translation.isFavorite() ? R.drawable.ic_favorite_yellow : R.drawable.ic_favorite_grey);
        holder.text.setText(translation.getText());
        holder.translation.setText(translation.getTranslation());
        holder.direction.setText(translation.getDirection().toString().toUpperCase());

        holder.favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onFavoriteIconClick(translation, holder.getAdapterPosition());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onItemClick(translation, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public Filter getFilter() {
        if (translationFilter == null) {
            translationFilter = new TranslationFilter(this, translationList);
        }
        return translationFilter;
    }

    public void updateFilter(List<Translation> filteredTranslationList) {
        filteredList.clear();
        filteredList.addAll(filteredTranslationList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    protected static class TranslationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_favorite)
        ImageView favoriteIcon;

        @BindView(R.id.tv_text)
        TextView text;

        @BindView(R.id.tv_translate)
        TextView translation;

        @BindView(R.id.tv_direction)
        TextView direction;

        public TranslationViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
