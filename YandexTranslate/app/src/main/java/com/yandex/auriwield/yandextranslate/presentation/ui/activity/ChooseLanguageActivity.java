package com.yandex.auriwield.yandextranslate.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.R2;
import com.yandex.auriwield.yandextranslate.domain.executor.impl.MainThreadImpl;
import com.yandex.auriwield.yandextranslate.domain.executor.impl.ThreadExecutor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.presentation.presenters.IChooseLanguagePresenter;
import com.yandex.auriwield.yandextranslate.presentation.presenters.impl.ChooseLanguagePresenter;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.translate.LanguageAdapter;
import com.yandex.auriwield.yandextranslate.storage.TranslationRepositoryImpl;

import java.util.List;

import butterknife.BindView;


import static com.yandex.auriwield.yandextranslate.presentation.ui.fragments.TranslateFragment.TYPE;

/**
 * @author g.zaripov
 */

public class ChooseLanguageActivity extends BaseActivity implements IChooseLanguagePresenter.View,
        LanguageAdapter.OnItemClickListener {

    public final static String SELECTED_LANGUAGE = "sel_lang";

    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    LanguageAdapter adapter;

    private Language.TYPE type;

    private ChooseLanguagePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_choose_language);

        int itemIndex = getIntent().getIntExtra(TYPE, Language.TYPE.SOURCE.ordinal());
        type = Language.TYPE.values()[itemIndex];

        initToolbar();

        initPresenter();

        initRecyclerView();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (type == Language.TYPE.SOURCE) {
            getSupportActionBar().setTitle(R.string.source_title);
        } else {
            getSupportActionBar().setTitle(R.string.target_title);
        }
    }

    private void initPresenter() {
        presenter = new ChooseLanguagePresenter(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new TranslationRepositoryImpl(this)
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void finishActivity() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void selectLanguage(Language language) {
        adapter.setSelectedLanguage(language);
    }

    private void initRecyclerView() {
        adapter = new LanguageAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(decor);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onReceiveSupportedLanguages(List<Language> languages) {
        adapter.setLanguages(languages);
        presenter.selectLanguage(type);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view, Language language) {
        presenter.updateDirection(type, language);
    }
}
