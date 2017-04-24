package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.translate.dictionary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DefinitionPart;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base.DataBinder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author g.zaripov
 */
public class MeanBinder extends DataBinder<MeanBinder.ViewHolder> {
    private static final String OPEN_PARENTHESIS = "(";
    private static final String CLOSE_PARENTHESIS = ")";
    private static final String COMMA_SPACE = ", ";

    private List<DefinitionPart> parts = new ArrayList<>();

    public MeanBinder(DictionaryAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.dic_item_mean, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        List<String> means = parts.get(position).getMeans();
        StringBuilder sb = new StringBuilder();
        sb.append(OPEN_PARENTHESIS);
        for (String mean : means)
            sb.append(mean).append(COMMA_SPACE);
        sb.replace(sb.lastIndexOf(COMMA_SPACE), sb.length(), CLOSE_PARENTHESIS);
        holder.tvMean.setText(sb.toString());
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

        @BindView(R.id.tv_mean)
        TextView tvMean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
