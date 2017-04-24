package com.yandex.auriwield.yandextranslate.presentation.ui.adapter.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ImageView;

import com.raizlabs.android.dbflow.annotation.Unique;
import com.yandex.auriwield.yandextranslate.presentation.ui.fragments.BaseFragment;
import com.yandex.auriwield.yandextranslate.presentation.ui.fragments.FavoritesFragment;
import com.yandex.auriwield.yandextranslate.presentation.ui.fragments.HistoryFragment;
import com.yandex.auriwield.yandextranslate.presentation.ui.fragments.Updatable;

/**
 * @author g.zaripov
 */
public class FavoriteStatePagerAdapter extends FragmentStatePagerAdapter {
    private static final int TABS_COUNT = 2;
    private static final int TAB_HISTORY = 0;
    private static final int TAB_FAVORITES = 1;

    private HistoryFragment historyFragment = new HistoryFragment();
    private FavoritesFragment favoritesFragment = new FavoritesFragment();

    private BaseFragment currentFragment;

    public FavoriteStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_HISTORY:
                return historyFragment;
            case TAB_FAVORITES:
                return favoritesFragment;
            default:
                return historyFragment;
        }
    }

    @Override
    public int getCount() {
        return TABS_COUNT;
    }

    public void update(int item, ImageView iv) {
        if (item == TAB_HISTORY && historyFragment != null) {
            historyFragment.onUpdate();
            historyFragment.handleImageView(iv);
            favoritesFragment.handleImageView(null);
        }
        if (item == TAB_FAVORITES && historyFragment != null) {
            favoritesFragment.onUpdate();
            favoritesFragment.handleImageView(iv);
            historyFragment.handleImageView(null);
        }
    }
}

