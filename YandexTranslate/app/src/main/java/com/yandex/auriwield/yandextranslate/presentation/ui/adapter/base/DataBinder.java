package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author g.zaripov
 */
public abstract class DataBinder<T extends RecyclerView.ViewHolder> {

    private DataBindAdapter mDataBindAdapter;

    public DataBinder(DataBindAdapter dataBindAdapter) {
        mDataBindAdapter = dataBindAdapter;
    }

    abstract public T newViewHolder(ViewGroup parent);

    abstract public void bindViewHolder(T holder, int position);

    abstract public int getItemCount();

    abstract public void clear();

}