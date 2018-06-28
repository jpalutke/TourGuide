package com.example.android.tourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * {@link CategoryAdapter} FragmentPagerAdapter that gives us the proper fragments for our categories
 */
class CategoryAdapter extends FragmentPagerAdapter {

    private final Context context;

    public CategoryAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    /**
     * Returns a Fragment to display
     *
     * @param position the position of the desired fragment in the adapter
     */
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FoodsFragment();
            case 1:
                return new ParksFragment();
            case 2:
                return new PoiFragment();
            case 3:
                return new HistoricalFragment();
            default:
                return new SceneryFragment();
        }
    }

    /**
     * Return total page count
     */
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.category_food);
            case 1:
                return context.getString(R.string.category_parks);
            case 2:
                return context.getString(R.string.category_poi);
            case 3:
                return context.getString(R.string.category_historical);
            default:
                return context.getString(R.string.category_scenery);
        }
    }
}
