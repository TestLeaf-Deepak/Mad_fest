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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Team_Viewpager extends AppCompatActivity {

    Toolbar toolbar;
    TextView games_toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    View view;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team__viewpager);

        Typeface typeface=Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");

        toolbar=(Toolbar)findViewById(R.id.teams_viewpager_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        games_toolbar=(TextView)findViewById(R.id.teams_toolbar_textview);
        games_toolbar.setTypeface(typeface);

        viewPager=(ViewPager)findViewById(R.id.teams_viewpager);
        setupViewPager(viewPager);



        tabLayout=(TabLayout)findViewById(R.id.teams_tablayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        typeface = Typeface.createFromAsset(getAssets(), "Fonts/OpenSans-Regular.ttf");
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom, null);
        tabOne.setTypeface(typeface);
        tabOne.setText("Chennai");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom, null);
        tabTwo.setTypeface(typeface);
        tabTwo.setText("Banglore");
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom, null);
        tabThree.setTypeface(typeface);
        tabThree.setText("Pune");
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Team_chennai());
        adapter.addFragment(new Team_banglore());
        adapter.addFragment(new Team_pune());

        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();


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

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);

        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return null;
        }
    }
}
