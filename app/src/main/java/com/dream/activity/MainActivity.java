package com.dream.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.david.wechatsample.gradientuilibrary.GradientIconView;
import com.david.wechatsample.gradientuilibrary.GradientTextView;
import com.dream.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    SectionsPagerAdapter mSectionsPagerAdapter;
    private List<GradientIconView> mTabIconIndicator = new ArrayList<GradientIconView>();
    private List<GradientTextView> mTabTextIndicator = new ArrayList<GradientTextView>();
    private GradientIconView mHomeIconView;
    private GradientIconView mflyDreamIconView;
    private GradientIconView mMyIconView;

    private GradientTextView mHomeChatTv;
    private GradientTextView mflyDreamTv;
    private GradientTextView mMyTv;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

    }


    private void initView() {

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        TextView tv = (TextView) findViewById(R.id.tv_title);
        tv.getPaint().setFakeBoldText(true);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mHomeIconView = (GradientIconView) findViewById(R.id.iconfont_home);
        mHomeIconView.setOnClickListener(this);
        mTabIconIndicator.add(mHomeIconView);
        mHomeIconView.setIconAlpha(1.0f);

        mflyDreamIconView = (GradientIconView) findViewById(R.id.iconfont_dream);
        mflyDreamIconView.setOnClickListener(this);
        mTabIconIndicator.add(mflyDreamIconView);

        mMyIconView = (GradientIconView) findViewById(R.id.iconfont_my);
        mMyIconView.setOnClickListener(this);
        mTabIconIndicator.add(mMyIconView);


        mHomeChatTv = (GradientTextView) findViewById(R.id.tv_home);
        mHomeChatTv.setOnClickListener(this);
        mTabTextIndicator.add(mHomeChatTv);

        mflyDreamTv = (GradientTextView) findViewById(R.id.tv_dream);
        mflyDreamTv.setOnClickListener(this);
        mTabTextIndicator.add(mflyDreamTv);

        mMyTv = (GradientTextView) findViewById(R.id.tv_my);
        mMyTv.setOnClickListener(this);
        mTabTextIndicator.add(mMyTv);

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(this);
    }

    private void resetOtherTabs() {
        resetOtherTabIcons();
        resetOtherTabText();
    }

    /**
     * 重置其他的Tab icon
     */
    private void resetOtherTabIcons() {
        for (int i = 0; i < mTabIconIndicator.size(); i++) {
            mTabIconIndicator.get(i).setIconAlpha(0);
        }
    }

    /**
     * 重置其他的Tab text
     */
    private void resetOtherTabText() {
        for (int i = 0; i < mTabTextIndicator.size(); i++) {
            mTabTextIndicator.get(i).setTextViewAlpha(0);
        }
    }

    @Override
    public void onClick(View v) {
        resetOtherTabs();
        switch (v.getId()) {
            case R.id.iconfont_home:
            case R.id.tv_home:
                mTabIconIndicator.get(0).setIconAlpha(1.0f);
                mTabTextIndicator.get(0).setTextViewAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.iconfont_dream:
            case R.id.tv_dream:
                mTabIconIndicator.get(1).setIconAlpha(1.0f);
                mTabTextIndicator.get(1).setTextViewAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.iconfont_my:
            case R.id.tv_my:
                mTabIconIndicator.get(2).setIconAlpha(1.0f);
                mTabTextIndicator.get(2).setTextViewAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
            GradientIconView iconLeft = mTabIconIndicator.get(position);
            GradientIconView iconRight = mTabIconIndicator.get(position + 1);

            GradientTextView textLeft = mTabTextIndicator.get(position);
            GradientTextView textRight = mTabTextIndicator.get(position + 1);

            iconLeft.setIconAlpha(1 - positionOffset);
            textLeft.setTextViewAlpha(1 - positionOffset);
            iconRight.setIconAlpha(positionOffset);
            textRight.setTextViewAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position)
            {
                case 0:
                    return new BaseRecyclerViewFragment();
                case 1:
                    return new MyDreamFragment();
                case 2:
                    return new MySpaceFragment();
                    default:
                        return new Fragment();
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

    }


}
