package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.translate.dictionary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DefinitionPart;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.Synonym;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base.DataBinder;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author g.zaripov
 */
public class TranslationBinder extends DataBinder<TranslationBinder.ViewHolder> {
    private static final String COMMA_SPACE = ", ";

    private List<DefinitionPart> parts = new ArrayList<>();

    public TranslationBinder(DictionaryAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.dic_item_translation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        holder.flowLayout.removeAllViews();
        DefinitionPart defPart = parts.get(position);

        holder.tvNumber.setText(String.format("%s ", defPart.getNumber()));

        TextView blue = getBlueTextView(holder.flowLayout);
        blue.setText(String.format("%s ", defPart.getText()));
        holder.flowLayout.addView(blue);

        TextView grey = getGreyTextView(holder.flowLayout);
        grey.setText(defPart.getGen());
        holder.flowLayout.addView(grey);

        for (Synonym synonym : defPart.getSynonyms()) {

            TextView comma = getBlueTextView(holder.flowLayout);
            comma.setText(COMMA_SPACE);
            holder.flowLayout.addView(comma);

            TextView b = getBlueTextView(holder.flowLayout);
            b.setText(synonym.getText());
            holder.flowLayout.addView(b);

            if (synonym.getGen() != null) {
                TextView g = getGreyTextView(holder.flowLayout);
                g.setText(String.format(" %s", synonym.getGen()));
                holder.flowLayout.addView(g);
            }
        }

    }

    private TextView getBlueTextView(ViewGroup viewGroup) {
        return (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.translation_tv_blue, viewGroup, false);
    }

    private TextView getGreyTextView(ViewGroup viewGroup) {
        return (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.translation_tv_grey, viewGroup, false);
    }

    @Override
    public int getItemCount() {
        return parts.size();
    }

    public void setDataSet(List<DefinitionPart> dataSet) {
        clear();
        parts.addAll(dataSet);
    }

    @Override
    public void clear() {
        parts.clear();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_number)
        TextView tvNumber;

        @BindView(R.id.flow_layout)
        FlowLayout flowLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
