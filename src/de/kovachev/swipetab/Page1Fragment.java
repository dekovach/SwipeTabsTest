package de.kovachev.swipetab;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public final class Page1Fragment extends Fragment implements OnTaskCompleted,  View.OnClickListener {

	private ProgressDialog pDialog;
	 private ImageView iv1;
    // URL to get contacts JSON
    private static String URL = "http://static.westwing.de/cms/test/product.json";
    public HashSet<String> path;
    public Iterator pathIter;
	
    public static Page1Fragment newInstance() {
        Page1Fragment fragment = new Page1Fragment();

         return fragment;
    }

 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View rootView = inflater.inflate(
                R.layout.page1_layout, container, false);
        rootView.setBackgroundColor(Color.WHITE);
        iv1 = (ImageView) rootView.findViewById(R.id.imageView1);
        
        RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.relativeLayoutWrapper);
        rl.setOnClickListener(Page1Fragment.this); 
        
        return rootView;
    }
    
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.relativeLayoutWrapper:
            	new getImage(getActivity(), getActivity().getApplicationContext(), this).execute();
            break;
        }   
    }


    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
        
    public void onTaskCompleted(Drawable d, String message) {
    	if (message != null && message != "") {
    		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    	}
        iv1 = (ImageView) getActivity().findViewById(R.id.imageView1);
//        iv1.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));; 
        iv1.getLayoutParams().width = 800;
        iv1.setImageDrawable(d);
    }
    
    private class getImage extends AsyncTask<Void, Void, Drawable> {
    	
    	private OnTaskCompleted listener;
        private Activity activity;
        private Context context;
        private String msg = null;
        
        public getImage(Activity activity, Context context, OnTaskCompleted listener) {
        	this.activity = activity;
        	this.context = context;
        	this.listener = listener;
        }
        
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
//    		pDialog = new ProgressDialog(activity);
//            pDialog.setMessage("loading...");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(true);
//            pDialog.show();
    	}
    	
		@Override
		protected Drawable doInBackground(Void... params) {
			if (path == null) {	
				String response = Utils.getJson(URL);
				if (response == null || response == "") {
					msg = "Network problems!";
					return null;
				} else {
					msg = null;
					path = Utils.parseResult(response);
				}
			}			
//	        Log.i("JSON", path);
			if (pathIter == null) {
				pathIter = path.iterator();
			}
			if (pathIter.hasNext()) {
		        URL imageUrl;
		        Drawable d = null;
				try {
					String nextUrl = pathIter.next().toString();
					msg = nextUrl;
					imageUrl = new URL(nextUrl);
			        InputStream content = (InputStream) imageUrl.getContent();
			        d = Drawable.createFromStream(content , "src"); 
				} catch (MalformedURLException e) {
					e.printStackTrace();
					msg = "Network problems!";
					return null;
				} catch (Exception e) {
					e.printStackTrace();
					msg = "Network problems!";
					return null;
				}
	
				return d;
			} else {
				pathIter = null;
			}
				
			return null;
		}
		
		protected void onPostExecute(Drawable d) {
//	        pDialog.dismiss();
	        //this next line causes a null pointer error
	        //note that I am throwing away the array list for now
	        //all I want to do is prove that I can call the Toast back in the calling Activity
	        listener.onTaskCompleted(d, msg);
	    }
    	
    }
    
    
}
