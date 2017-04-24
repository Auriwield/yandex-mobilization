package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.translate;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.R2;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author g.zaripov
 */
public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

    private List<Language> languages;

    private int selectedPos = -1;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, Language language);

    }


    public LanguageAdapter() {
    }

    public LanguageAdapter(List<Language> languages, Language selectedLanguage) {
        this.languages = languages;
        if (languages != null)
            selectedPos = languages.indexOf(selectedLanguage);
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choose_language_item, parent, false);
        return new LanguageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final LanguageViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (onItemClickListener != null && languages != null && languages.size() > pos)
                    onItemClickListener.onClick(v, languages.get(pos));
            }
        });
        holder.tvLang.setText(languages.get(position).getName());
        if (position != selectedPos) {
            holder.ivDone.setVisibility(View.GONE);
            holder.itemView.setBackgroundColor(Color.WHITE);
        } else {
            holder.ivDone.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(),
                    R.color.item_selected_color));
        }
    }

    @Override
    public int getItemCount() {
        return languages == null ? 0 : languages.size();
    }

    public void setSelectedLanguage(Language selectedLanguage) {
        int temp = selectedPos;
        selectedPos = languages.indexOf(selectedLanguage);
        if (temp != -1)
            notifyItemChanged(temp);
        notifyItemChanged(selectedPos);
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    static class LanguageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.tv_lang)
        TextView tvLang;
        @BindView(R2.id.iv_done)
        ImageView ivDone;

        LanguageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
