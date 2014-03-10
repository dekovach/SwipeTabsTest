package de.kovachev.swipetab;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

import de.kovachev.android.utils.TypeFaceLoader;
import de.kovachev.swipetab.PopoverView.PopoverViewDelegate;

/**
 * Main Activity in the Westwing test app
 *
 */
public class MainActivity extends BaseActivity implements PopoverViewDelegate{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setFooterIndicatorStyle(IndicatorStyle.Triangle);
        indicator.setTypeface(TypeFaceLoader.getBrandonMedium());
        mIndicator = indicator;
    }
    
    /**
     * Called when the button pn Page 2 is clicked
     * @param view
     */
    public void btnClicked(View view) {
    	//get root layout
    			FrameLayout rootView = (FrameLayout)findViewById(R.id.FrameLayout1);

    			PopoverView popoverView = new PopoverView(this, R.layout.popover_showed_view);
    			popoverView.setContentSizeForViewInPopover(new Point(320, 340));
    			popoverView.setDelegate(this);
    			popoverView.showPopoverFromRectInViewGroup(rootView, PopoverView.getFrameForView(view), PopoverView.PopoverArrowDirectionAny, true);

    }

	@Override
	public void popoverViewWillShow(PopoverView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popoverViewDidShow(PopoverView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popoverViewWillDismiss(PopoverView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popoverViewDidDismiss(PopoverView view) {
		// TODO Auto-generated method stub
		
	}
    
}