package com.yandex.auriwield.yandextranslate.presentation.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.domain.executor.impl.MainThreadImpl;
import com.yandex.auriwield.yandextranslate.domain.executor.impl.ThreadExecutor;
import com.yandex.auriwield.yandextranslate.domain.model.dictionary.DictionaryEntry;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Translation;
import com.yandex.auriwield.yandextranslate.presentation.presenters.ITranslatePresenter;
import com.yandex.auriwield.yandextranslate.presentation.presenters.impl.TranslatePresenter;
import com.yandex.auriwield.yandextranslate.presentation.ui.activity.ChooseLanguageActivity;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.history.HistoryAdapter;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.translate.dictionary.DictionaryAdapter;
import com.yandex.auriwield.yandextranslate.storage.TranslationRepositoryImpl;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;
import butterknife.OnTouch;

import static android.app.Activity.RESULT_OK;

public class TranslateFragment extends BaseFragment implements ITranslatePresenter.View, View.OnKeyListener {
    public static final String TITLE = "translate_fragment";

    public static final String TYPE = "type";

    private static final int REQUEST_CODE = 0x1337;

    @BindView(R.id.tv_source_lang)
    TextView tvSource;

    @BindView(R.id.tv_target_lang)
    TextView tvTarget;

    @BindView(R.id.translate_edit_text)
    EditText editText;

    @BindView(R.id.text_src)
    TextView tvTextSource;

    @BindView(R.id.translation_fragment_root)
    LinearLayout layout;

    @BindView(R.id.iv_close)
    ImageView ivClear;

    @BindView(R.id.iv_favorite)
    ImageView ivFavorite;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ITranslatePresenter presenter;
    private DictionaryAdapter adapter;

    private boolean lastUserVisible = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.translate_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        editText.setOnKeyListener(this);
    }

    private void initRecyclerView() {
        adapter = new DictionaryAdapter();
        //mAdapter.setListener(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                hideKeyboard(rv);
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setUserVisibleHint(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        setUserVisibleHint(false);
    }

    private void initPresenter() {
        presenter = new TranslatePresenter(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new TranslationRepositoryImpl(getContext())
        );
        super.initPresenter(presenter);
    }

    @OnTouch(R.id.iv_close)
    boolean clear(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ivClear.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.colorAccent));
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            presenter.onClear();
            return true;
        }

        return false;
    }


    @OnFocusChange(R.id.translate_edit_text)
    void toggleKeyboard(View v, boolean hasFocus) {
        InputMethodManager systemService = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (hasFocus) {
            // Open keyboard
            systemService.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        } else {
            // Close keyboard
            systemService.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    @OnClick(R.id.translation_fragment_root)
    void hideKeyboard(View view) {
        if (getActivity() == null) return;
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        layout.requestFocus();
    }

    @OnClick(R.id.iv_favorite)
    void operateFavorites(View view) {
        presenter.onFavoriteIconClick();
    }

    @Override
    public void updateFavoritesIcon(Translation translation) {
        if (translation.isFavorite()) {
            ivFavorite.setVisibility(View.VISIBLE);
            ivFavorite.setImageResource(R.drawable.ic_favorite_yellow);
        } else {
            ivFavorite.setVisibility(View.VISIBLE);
            ivFavorite.setImageResource(R.drawable.ic_favorite_grey);
        }
    }

    @Override
    public void clearView() {
        ivClear.setColorFilter(ContextCompat.getColor(getContext(), R.color.image_secondary));
        ivClear.setVisibility(View.GONE);
        editText.getText().clear();
        tvTextSource.setText("");
        adapter.clear();
        ivFavorite.setVisibility(View.GONE);
    }

    @Override
    public void updateEditText(String text) {
        if (!editText.getText().toString().trim().equals(text)) {
            editText.setText(text);
            editText.setSelection(editText.getText().length());
        }
    }

    @OnTextChanged(R.id.translate_edit_text)
    void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().isEmpty()) {
            presenter.onClear();
        } else {
            ivClear.setVisibility(View.VISIBLE);
        }
        presenter.translate(s.toString());
    }

    @OnClick(R.id.iv_switch)
    void reverseDirection(View view) {
        presenter.reverseDirection();
    }

    @OnClick(R.id.tv_source_lang)
    void chooseSourceLanguage(View view) {
        presenter.chooseLanguage(Language.TYPE.SOURCE);
    }

    @OnClick(R.id.tv_target_lang)
    void chooseTargetLanguage(View view) {
        presenter.chooseLanguage(Language.TYPE.TARGET);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (lastUserVisible == isVisibleToUser) return;
        lastUserVisible = isVisibleToUser;
        if (isVisibleToUser) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (editText != null)
                        editText.requestFocus();
                }
            }, 200);
            return;
        }
        hideKeyboard(getView());
        if (layout != null)
            layout.requestFocus();
    }


    @Override
    public void showTranslation(Translation translation) {
        tvTextSource.setText(translation.getTranslation());
    }

    @Override
    public void updateDirection(Direction direction) {
        tvSource.setText(direction.getSource().getName());
        tvTarget.setText(direction.getTarget().getName());
    }

    @Override
    public void applyLanguage(String lang) {

    }

    @Override
    public void showLanguagesActivity(Language.TYPE type) {
        Intent intent = new Intent(getContext(), ChooseLanguageActivity.class);
        intent.putExtra(TYPE, type.ordinal());
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void showDictionaryEntry(DictionaryEntry entry) {
        adapter.updateData(entry);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            String string = editText.getText().toString();
            if (!string.isEmpty()) presenter.translate(string);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        // If the event is a key-down event on the "enter" button
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
            hideKeyboard(v);
            return true;
        }
        return false;
    }
}
