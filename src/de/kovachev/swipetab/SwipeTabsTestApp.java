package de.kovachev.swipetab;

import de.kovachev.android.utils.TypeFaceLoader;
import android.app.Application;

public class SwipeTabsTestApp extends Application {

	private static SwipeTabsTestApp sAppInstance = null;
	
	public static SwipeTabsTestApp getInstance()
	  {
	    return sAppInstance;
	  }
	
	 public void onCreate() {
		 super.onCreate();
		    sAppInstance = this;
		 TypeFaceLoader.init(getAssets());
	  }
}