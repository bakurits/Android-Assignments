package com.example.assignment2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.assignment2.Models.Country;

import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    List<Country> countries;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentAdapter(FragmentManager fm, List<Country> countries) {
        super(fm);
        this.countries = countries;
    }

    @Override
    public Fragment getItem(int i) {
        return MainFragment.newInstance(countries.get(i).getName());
    }

    @Override
    public int getCount() {
        return countries.size();
    }
}
