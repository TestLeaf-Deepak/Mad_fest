package com.example.pratheesh.madfest_sample;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Games_Detail extends AppCompatActivity {

    Toolbar gamedetailstoolbar;
    TextView gamedetails_toolbar_textview;
    TabLayout tabLayout;
    ViewPager viewPager;
    View view;
    Typeface typeface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games__detail);

        typeface=Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");



        gamedetailstoolbar=(Toolbar)findViewById(R.id.game_details_toolbar);
        setSupportActionBar(gamedetailstoolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gamedetails_toolbar_textview=(TextView)findViewById(R.id.game_details_toolbar_textview);
        gamedetails_toolbar_textview.setTypeface(typeface);

        viewPager=(ViewPager)findViewById(R.id.game_details__viewpager);
        setupViewPager(viewPager);

        tabLayout=(TabLayout)findViewById(R.id.game_details_tablayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }


    private void setupTabIcons() {
        typeface = Typeface.createFromAsset(getAssets(), "Fonts/OpenSans-Regular.ttf");
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom, null);
        tabOne.setTypeface(typeface);
        tabOne.setText("Games Rules");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        typeface = Typeface.createFromAsset(getAssets(), "Fonts/OpenSans-Regular.ttf");
        TextView tabtwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom, null);
        tabtwo.setTypeface(typeface);
        tabtwo.setText("Games Result");
        tabLayout.getTabAt(1).setCustomView(tabtwo);


    }


    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Games_Rules(), "gamesresults");
        adapter.addFragment(new Games_Results(), "gamesrules");



        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
