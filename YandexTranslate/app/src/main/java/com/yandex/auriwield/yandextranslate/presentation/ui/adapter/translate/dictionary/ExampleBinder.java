package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.translate.dictionary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.Example;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base.DataBinder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author g.zaripov
 */
public class ExampleBinder extends DataBinder<ExampleBinder.ViewHolder> {
    private static final String DELIMITER = " — ";

    private List<Example> parts = new ArrayList<>();

    public ExampleBinder(DictionaryAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.dic_item_example, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        Example example = parts.get(position);
        holder.tvExample.setText(example.getText() + DELIMITER + example.getTranslation().get(0));
    }

    @Override
    public int getItemCount() {
        return parts.size();
    }

    public void setDataSet(List<Example> dataSet) {
        clear();
        parts.addAll(dataSet);
    }

    @Override
    public void clear() {
        parts.clear();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_example)
        TextView tvExample;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
