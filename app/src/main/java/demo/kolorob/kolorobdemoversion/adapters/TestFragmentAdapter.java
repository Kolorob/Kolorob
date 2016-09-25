package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by israt.jahan on 9/18/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.fragment.TestFragment;

public class TestFragmentAdapter extends FragmentPagerAdapter {
    protected static final String[] CONTENT = new String[] { "This", "Is", "A", "Test", "hello"};
    private int[] offerImages = {
            R.drawable.user_manual_page1,
            R.drawable.user_manual_page2,
            R.drawable.user_manual_page3,
            R.drawable.user_manual_page4, R.drawable.user_manual_page5
    };

    private int mCount = CONTENT.length;

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new TestFragment(offerImages[position]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TestFragmentAdapter.CONTENT[position % CONTENT.length];
    }


    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}