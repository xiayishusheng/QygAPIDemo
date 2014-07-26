package qyg.apidemo.fragments;

import qyg.apidemo.R;
import utils.Constants;
import utils.Utils;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment {
	private String TAG = Constants.logPrefix + getClass().getCanonicalName();
	
    @Override
    public void onAttach(Activity activity) {
    	Utils.logd(TAG, "--->onAttach()");
        super.onAttach(activity);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Utils.logd(TAG, "--->onCreate()");
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Utils.logd(TAG, "--->onCreateView()");
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	Utils.logd(TAG, "--->onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
    	Utils.logd(TAG, "--->onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
    	Utils.logd(TAG, "--->onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
    	Utils.logd(TAG, "--->onPause()");
        super.onPause();
    }
    
    @Override
    public void onStop() {
    	Utils.logd(TAG, "--->onStop()");
        super.onStop();
    }
    
    @Override
    public void onDestroyView() {
    	Utils.logd(TAG, "--->onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
    	Utils.logd(TAG, "--->onDestroy()");
        super.onDestroy();
    }
    
    @Override
    public void onDetach() {
    	Utils.logd(TAG, "--->onDetach()");
        super.onDetach();
    }
}
