package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author g.zaripov
 */
public abstract class DataBindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getDataBinder(viewType).newViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int binderPosition = getBinderPosition(position);
        getDataBinder(viewHolder.getItemViewType()).bindViewHolder(viewHolder, binderPosition);
    }

    @Override
    public abstract int getItemCount();

    @Override
    public abstract int getItemViewType(int position);

    public abstract <T extends DataBinder> T getDataBinder(int viewType);

    public abstract int getPosition(DataBinder binder, int binderPosition);

    public abstract int getBinderPosition(int position);


}