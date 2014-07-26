package qyg.apidemo.activity;

import qyg.apidemo.fragments.FragmentsActivity;
import utils.Constants;
import utils.Utils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class FirstActivity extends Activity {

	private String TAG = Constants.logPrefix + getClass().getCanonicalName();
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Utils.logd(TAG, "--->onCreate()");
		mContext = this;
		LinearLayout layout = new LinearLayout(mContext);
		Button button = new Button(mContext);
		button.setText("Open");
		layout.addView(button);
		setContentView(layout);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent1 = new Intent("qyg.apidemo.activity.SecondActivity");
				Intent intent2 = new Intent(mContext, FragmentsActivity.class);
				
				Intent intent = Intent.createChooser(intent1, "Choose Title...");
				//startActivity(intent1);
				startActivity(intent);
			
			}
		});
	}
	
	@Override
	protected void onStart() {
		Utils.logd(TAG, "--->onStart()");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Utils.logd(TAG, "--->onRestart()");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Utils.logd(TAG, "--->onResume()");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Utils.logd(TAG, "--->onPause()");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Utils.logd(TAG, "--->onStop()");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Utils.logd(TAG, "--->onDestroy()");
		super.onDestroy();
	}

}
