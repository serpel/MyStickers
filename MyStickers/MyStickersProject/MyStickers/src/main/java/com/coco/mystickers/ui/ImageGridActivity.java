/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coco.mystickers.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.coco.mystickers.BuildConfig;
import com.coco.mystickers.R;
import com.coco.mystickers.util.MainPagerAdapter;
import com.coco.mystickers.util.Utils;

import java.util.Locale;

/**
 * Simple FragmentActivity to hold the main {@link ImageGridFragment} and not much else.
 */
public class ImageGridActivity extends FragmentActivity {
    private static final String TAG = "ImageGridActivity";
    public static String []menus;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);

        loadItems();
        setContentView(R.layout.image_detail_pager);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), menus);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

//        if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
//            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.add(android.R.id.content, new ImageGridFragment(), TAG);
//            ft.commit();
//        }
        mViewPager.setCurrentItem(mSectionsPagerAdapter.getHomePosition());
    }


    public void loadItems()
    {
        // load menus titles
        Resources resources = getResources();
        menus = resources.getStringArray(R.array.menu_array);
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        private String []menus;
        public SectionsPagerAdapter(FragmentManager fm, String[] menus) {
            super(fm);
            this.menus = menus;
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            switch (position)
            {
                case 0:
                    //Categories
                    fragment = ImageGridFragment.newInstance();
                case 1:
                    //Home
                    fragment = ImageGridFragment.newInstance();
                case 2:
                    //Favorites
                    fragment = ImageGridFragment.newInstance();
                default:
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return menus.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
           return menus.length > position ? this.menus[position] : "More Items ...";
        }

        public int getHomePosition()
        {
            for(int i=0; i<menus.length; i++)
            {
                if(menus[i].equals("Home")){
                    return i;
                }
            }
            return 0;
        }
    }

}
