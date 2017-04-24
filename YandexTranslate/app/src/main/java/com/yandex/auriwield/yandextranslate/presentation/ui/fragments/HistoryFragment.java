package com.yandex.auriwield.yandextranslate.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.domain.executor.impl.MainThreadImpl;
import com.yandex.auriwield.yandextranslate.domain.executor.impl.ThreadExecutor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.presentation.presenters.IHistoryPresenter;
import com.yandex.auriwield.yandextranslate.presentation.presenters.impl.HistoryPresenter;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.history.HistoryAdapter;
import com.yandex.auriwield.yandextranslate.storage.TranslationRepositoryImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.OnTextChanged;

/**
 * @author g.zaripov
 */

public class HistoryFragment extends BaseFragment implements IHistoryPresenter.View, Updatable {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.void_list_view)
    LinearLayout stubView;
    @BindView(R.id.search_view_root)
    View searchView;
    @BindView(R.id.search_view)
    EditText editText;

    private HistoryAdapter mAdapter;
    private IHistoryPresenter presenter;
    private ImageView ivDelete;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        editText.clearFocus();
        editText.setHint(R.string.history_search_view_hint);
    }

    private void initPresenter() {
        presenter = new HistoryPresenter(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new TranslationRepositoryImpl(getContext())
        );
        super.initPresenter(presenter);
    }

    private void initRecyclerView() {
        mAdapter = new HistoryAdapter();
        mAdapter.setListener(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        recyclerView.addItemDecoration(decor);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onUpdate() {
        if (presenter != null)
            presenter.getAllTranslations();
    }

    @Override
    public void handleImageView(ImageView iv) {
        ivDelete = iv;
        if (iv != null)
            iv.setOnClickListener(presenter);
    }

    @OnTextChanged(R.id.search_view)
    void onTextChanged(CharSequence s, int start, int before, int count) {
        mAdapter.getFilter().filter(s);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (presenter != null)
                presenter.getAllTranslations();
            if (ivDelete != null)
                ivDelete.setVisibility(mAdapter.getTranslationList().isEmpty()
                        ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void updateData(List<Translation> data) {
        int visibility = data.isEmpty() ? View.GONE : View.VISIBLE;

        if (ivDelete != null)
            ivDelete.setVisibility(visibility);

        stubView.setVisibility(data.isEmpty() ? View.VISIBLE : View.GONE);
        searchView.setVisibility(visibility);
        mAdapter.updateData(data);
    }



    @Override
    public void updateItem(Translation data, int position) {
        mAdapter.updateData(data, position);
    }

    @Override
    public void clear() {
        mAdapter.clear();
        stubView.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.GONE);
        if (ivDelete != null)
            ivDelete.setVisibility(View.GONE);
    }
}
