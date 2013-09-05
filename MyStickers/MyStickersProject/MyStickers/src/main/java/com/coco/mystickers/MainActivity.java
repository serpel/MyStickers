package com.coco.mystickers;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    ViewPager mViewPager;
    TabsPagerAdapter mTabsPagerAdapter;
    public static String []menus;

    public void loadItems()
    {
        // load menus titles
        Resources resources = getResources();
        menus = resources.getStringArray(R.array.menu_array);

       // final ActionBar actionBar = getActionBar();
    }

    public static int getHomePosition()
    {
        for(int i=0; i<menus.length; i++)
        {
            if(menus[i].equals("Home")){
                return i;
            }
        }
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadItems();

        // create adapter
        mTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        // set adapter to view
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mTabsPagerAdapter);

        // set Home
        mViewPager.setCurrentItem(getHomePosition());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void openSearch()
    {

    }

    void openSettings()
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //MenuItem searchItem = menu.findItem(R.id.action_search);
        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        //setupSearchView(searchView);

        return true;
    }

    /* Adaptador Fragment */
    public static class TabsPagerAdapter extends FragmentStatePagerAdapter
    {
        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            Fragment fragment = new ObjectFragment();
            Bundle args = new Bundle();
            args.putInt(ObjectFragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return menus.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            if (menus.length >=  position)
            {
                return menus[position];
            }

            return "More Items";
        }
    }

    /* Objeto visible en la vista */
    public static class ObjectFragment extends Fragment
    {
        public static final String ARG_OBJECT = "tab";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_object,container,false);
            //Bundle args = getArguments();
            //((TextView) view.findViewById(android.R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
            GridView gridView = (GridView) view.findViewById(R.id.grid_view);
            gridView.setAdapter(new ImageAdapter(this.getActivity()));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(view.getContext(), " pos : " + i, Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }
}


