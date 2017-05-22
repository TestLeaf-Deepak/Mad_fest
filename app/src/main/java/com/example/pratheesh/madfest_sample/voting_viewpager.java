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

public class voting_viewpager extends AppCompatActivity {

    Toolbar toolbar;
    TextView voting_toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    View view;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_viewpager);

        Typeface typeface=Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");

        toolbar=(Toolbar)findViewById(R.id.voting_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        voting_toolbar=(TextView)findViewById(R.id.voting_toolbar_textview);
        voting_toolbar.setTypeface(typeface);

        viewPager=(ViewPager)findViewById(R.id.voting_viewpager);
        setupViewPager(viewPager);



        tabLayout=(TabLayout)findViewById(R.id.voting_tablayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


    }

    private void setupTabIcons() {
        typeface = Typeface.createFromAsset(getAssets(), "Fonts/OpenSans-Regular.ttf");
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom, null);
        tabOne.setTypeface(typeface);
        tabOne.setText("Voting 1");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom, null);
        tabTwo.setTypeface(typeface);
        tabTwo.setText("Voting 2");
        tabLayout.getTabAt(1).setCustomView(tabTwo);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new voting_frame1());
        adapter.addFragment(new voting_frame2());



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
