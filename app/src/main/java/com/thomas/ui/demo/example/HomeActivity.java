package com.thomas.ui.demo.example;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thomas.ui.adapter.ThomasFragmentStateAdapter;
import com.thomas.ui.demo.R;
import com.thomas.ui.demo.base.DemoActivity;
import com.thomas.ui.demo.fragment.FoundFragment;
import com.thomas.ui.demo.fragment.IndexFragment;
import com.thomas.ui.demo.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends DemoActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.nav_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.vp_home)
    ViewPager2 vpHome;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        fragments.add(IndexFragment.newInstance());
        fragments.add(FoundFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        vpHome.setAdapter(new ThomasFragmentStateAdapter(mActivity, fragments));
        vpHome.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        vpHome.setUserInputEnabled(false);
        vpHome.setOffscreenPageLimit(fragments.size());
    }

    @Override
    public void doBusiness() {

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                vpHome.setCurrentItem(0, false);
                return true;
            case R.id.home_found:
                vpHome.setCurrentItem(1, false);
                return true;
            case R.id.home_me:
                vpHome.setCurrentItem(2, false);
                return true;
            default:
                break;
        }
        return false;
    }

}
