package de.kovachev.swipetab;

import de.kovachev.android.utils.TypeFaceLoader;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public final class Page2Fragment extends Fragment {

    public static Page2Fragment newInstance() {
        Page2Fragment fragment = new Page2Fragment();

         return fragment;
    }

 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View rootView = inflater.inflate(
                R.layout.page2_layout, container, false);
        rootView.setBackgroundColor(Color.WHITE);
        Button btn1 = (Button) rootView.findViewById(R.id.button1);
        btn1.setTypeface(TypeFaceLoader.getBrandonMedium());
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    
}
