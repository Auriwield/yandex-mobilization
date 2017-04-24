package com.yandex.auriwield.yandextranslate.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.R2;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.fragment.FavoriteStatePagerAdapter;

import butterknife.BindView;

public class FavoriteFragment extends BaseFragment {

    public static final String TITLE = "favorite_fragment";

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;

    private FavoriteStatePagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new FavoriteStatePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(R.string.history);
        tabLayout.getTabAt(1).setText(R.string.favorites);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && viewPager != null) {
            adapter.update(viewPager.getCurrentItem(), ivDelete);
        }
    }
}
