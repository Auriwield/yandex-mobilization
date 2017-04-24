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
public class TranscriptionBinder extends DataBinder<TranscriptionBinder.ViewHolder> {
    private static final String OPEN_PARENTHESIS = "[";
    private static final String CLOSE_PARENTHESIS = "]";
    private static final String EMPTY = "";

    private String text = EMPTY;
    private String transcription = EMPTY;

    public TranscriptionBinder(DictionaryAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.dic_item_transcription, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        holder.tvDicText.setText(text);
        if (!transcription.isEmpty())
            holder.tvDicTs.setText(String.format("[%s]", transcription));
    }

    @Override
    public int getItemCount() {
        return text.isEmpty() || transcription.isEmpty() ? 0 : 1;
    }

    public void setDataSet(String text, String transcription) {
        this.text = text;
        this.transcription = transcription;
    }

    @Override
    public void clear() {
        text = EMPTY;
        transcription = EMPTY;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.dic_text)
        TextView tvDicText;

        @BindView(R.id.dic_ts)
        TextView tvDicTs;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
