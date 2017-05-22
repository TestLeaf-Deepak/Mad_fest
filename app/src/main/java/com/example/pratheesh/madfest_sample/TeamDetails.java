package com.example.pratheesh.madfest_sample;

import android.graphics.Typeface;
import android.provider.Telephony;
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

public class TeamDetails extends AppCompatActivity {

    Toolbar teamdetailstoolbar;
    TextView teamdetails_toolbar_textview;
    TabLayout tabLayout;
    ViewPager viewPager;
    View view;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        typeface=Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");



        teamdetailstoolbar=(Toolbar)findViewById(R.id.team_details_toolbar);
        setSupportActionBar(teamdetailstoolbar);
          getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        teamdetails_toolbar_textview=(TextView)findViewById(R.id.team_details_toolbar_textview);
        teamdetails_toolbar_textview.setTypeface(typeface);

        viewPager=(ViewPager)findViewById(R.id.team_details__viewpager);
        setupViewPager(viewPager);

        tabLayout=(TabLayout)findViewById(R.id.team_details_tablayout);
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
        tabOne.setText("Team Info");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        typeface = Typeface.createFromAsset(getAssets(), "Fonts/OpenSans-Regular.ttf");
        TextView tabtwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom, null);
        tabtwo.setTypeface(typeface);
        tabtwo.setText("Team members");
        tabLayout.getTabAt(1).setCustomView(tabtwo);


    }


    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Team_details_logo(), "teaminfo");
        adapter.addFragment(new team_details_teammembers(), "teammembers");



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
