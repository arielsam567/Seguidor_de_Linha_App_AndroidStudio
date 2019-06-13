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
    private String mAddress;


    public SectionsPagerAdapter(Context context, FragmentManager fm, String address) {
        super(fm);
        mContext = context;
        mAddress = address;
    }

    @Override
    public Fragment getItem(int position) {
        // retorna o Fragment de acordo com o número da página do ViewPager;
        switch (position){
            case 0:
                return TelaJunior.newInstance(position +1, mAddress);
            case 1:
                return TelaPro8s.newInstance(position + 1);
            case 2:
                return TelaPro6s.newInstance(position + 1);
            default:
                return TelaJunior.newInstance(position + 1, mAddress);
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