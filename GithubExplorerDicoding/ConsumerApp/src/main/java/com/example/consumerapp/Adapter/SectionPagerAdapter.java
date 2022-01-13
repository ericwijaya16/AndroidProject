package com.example.consumerapp.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.consumerapp.Fragments.FollowerFragment;
import com.example.consumerapp.Fragments.FollowingFragment;

public class SectionPagerAdapter extends FragmentStateAdapter {

    public SectionPagerAdapter(AppCompatActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0: fragment = new FollowerFragment();
            break;

            case 1: fragment = new FollowingFragment();
            break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
