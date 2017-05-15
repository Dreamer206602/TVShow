package com.booboomx.tvshow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.booboomx.tvshow.Ui.fragemnt.FollowFragment;
import com.booboomx.tvshow.Ui.fragemnt.HomeFragment;
import com.booboomx.tvshow.Ui.fragemnt.LiveFragment;
import com.booboomx.tvshow.Ui.fragemnt.MineFragment;
import com.booboomx.tvshow.base.BaseActivity;
import com.booboomx.tvshow.widget.BottomTabView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.booboomx.tvshow.R.id.bottomTabView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(bottomTabView)
    BottomTabView mBottomTabView;


    private FragmentPagerAdapter mPagerAdapter;



    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {

        mPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragments().get(position);
            }

            @Override
            public int getCount() {
                return getFragments().size()>0?getFragments().size():0;
            }
        };


        if (getCenterView() == null){
            mBottomTabView.setTabItemViews(getTabViews());
        }else {
            mBottomTabView.setTabItemViews(getTabViews(), getCenterView());
        }
        mViewPager.setAdapter(mPagerAdapter);


    }

    @Override
    public void setListener() {

        mBottomTabView.setOnTabItemSelectListener(new BottomTabView.OnTabItemSelectListener() {
            @Override
            public void onTabItemSelect(int position) {
                mViewPager.setCurrentItem(position,true);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mBottomTabView.updatePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {

        List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();
        tabItemViews.add(new BottomTabView.TabItemView(this, "首页",
                R.color.room_table_text_color,
                R.color.colorAccent,
                R.drawable.btn_tabbar_home_normal,
                R.drawable.btn_tabbar_home_selected));
        tabItemViews.add(new BottomTabView.TabItemView(this,
                "直播",
                R.color.room_table_text_color,
                R.color.colorAccent,
                R.drawable.btn_tabbar_zhibo_normal,
                R.drawable.btn_tabbar_zhibo_selected));
        tabItemViews.add(new BottomTabView.TabItemView(this, "关注",
                R.color.room_table_text_color,
                R.color.colorAccent,
                R.drawable.btn_tabbar_guanzhu_normal,
                R.drawable.btn_tabbar_guanzhu_selected));
        tabItemViews.add(new BottomTabView.TabItemView(this,
                "我的", R.color.room_table_text_color,
                R.color.colorAccent,
                R.drawable.btn_tabbar_wode_normal,
                R.drawable.btn_tabbar_wode_selected));
        return tabItemViews;


    }

    @Override
    protected List<Fragment> getFragments() {
         List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new LiveFragment());
        mFragments.add(new FollowFragment());
        mFragments.add(new MineFragment());
        return mFragments;
    }


}
