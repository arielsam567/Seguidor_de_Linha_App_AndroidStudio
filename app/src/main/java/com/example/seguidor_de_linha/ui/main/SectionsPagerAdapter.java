package com.example.seguidor_de_linha.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.seguidor_de_linha.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3 };
    private final Context mContext;


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // retorna o Fragment de acordo com o número da página do ViewPager;
        switch (position){
            case 0:
                return PlaceholderFragmentOne.newInstance(position +1);
            case 1:
                return PlaceholderFragmentTwo.newInstance(position + 1);
            case 2:
                return PlaceholderFragmentThree.newInstance(position + 1);
            default:
                return PlaceholderFragmentOne.newInstance(position + 1);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //pega os titulos das paginas
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // quantidade de paginas mostradas.
        return 3;
    }
}