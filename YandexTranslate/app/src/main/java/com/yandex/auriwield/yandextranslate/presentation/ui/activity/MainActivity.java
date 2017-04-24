package com.yandex.auriwield.yandextranslate.presentation.ui.activity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.yandex.auriwield.yandextranslate.R;
import com.yandex.auriwield.yandextranslate.R2;
import com.yandex.auriwield.yandextranslate.presentation.ui.adapter.fragment.ViewPagerAdapter;
import com.yandex.auriwield.yandextranslate.presentation.ui.fragments.FavoriteFragment;
import com.yandex.auriwield.yandextranslate.presentation.ui.fragments.SettingsFragment;
import com.yandex.auriwield.yandextranslate.presentation.ui.fragments.TranslateFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R2.id.tabs)
    TabLayout tabLayout;
    @BindView(R2.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            setupViewPager(viewPager);
            setupTabLayout();
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_translate);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_favorite);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_settings);

        final int item_selected_color = ContextCompat.getColor(this, R.color.icon_color_selected);
        final int item_unselected_color = ContextCompat.getColor(this, R.color.icon_color_unselected);

        tabLayout.getTabAt(0).getIcon().setColorFilter(item_selected_color, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(item_unselected_color, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(item_unselected_color, PorterDuff.Mode.SRC_IN);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(item_selected_color, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(item_unselected_color, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TranslateFragment(), TranslateFragment.TITLE);
        adapter.addFragment(new FavoriteFragment(), FavoriteFragment.TITLE);
        adapter.addFragment(new SettingsFragment(), SettingsFragment.TITLE);
        viewPager.setAdapter(adapter);
    }

}
