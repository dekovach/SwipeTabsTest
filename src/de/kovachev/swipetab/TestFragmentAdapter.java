package de.kovachev.swipetab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * Test Fragment Adapter to provide the Fragments to the ViewPager
 *
 */
class TestFragmentAdapter extends FragmentPagerAdapter  {
    protected static final String[] CONTENT = new String[] { "Page 1", "Page 2" };
 
    private int mCount = CONTENT.length;

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
        	return Page1Fragment.newInstance();
        } else {
        	return Page2Fragment.newInstance();
        }
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