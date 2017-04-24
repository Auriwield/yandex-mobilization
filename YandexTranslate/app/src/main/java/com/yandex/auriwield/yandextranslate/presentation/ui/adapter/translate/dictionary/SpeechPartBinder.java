package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.translate.dictionary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base.DataBinder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author g.zaripov
 */
public class SpeechPartBinder extends DataBinder<SpeechPartBinder.ViewHolder> {

    private List<String> mDataSet = new ArrayList<>();

    public SpeechPartBinder(DictionaryAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.dic_item_speech_part, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        holder.tvSpeechPart.setText(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setDataSet(List<String> dataSet) {
        clear();
        mDataSet.addAll(dataSet);
    }

    @Override
    public void clear() {
        mDataSet.clear();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_speech_part)
        TextView tvSpeechPart;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
