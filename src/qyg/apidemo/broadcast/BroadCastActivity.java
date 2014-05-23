package qyg.apidemo.broadcast;

import qyg.apidemo.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadCastActivity extends Activity {

	private Context mContext;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_broadcast);
		mContext = this;
		//registerBoradcastReceiver();
		mButton = (Button) findViewById(R.id.button1);
		mButton.setText("·¢ËÍ¹ã²¥");
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent("MY_ACTION_NAME");
				mIntent.putExtra("BroadCastData", "Send BroadCast Data!");

				//Send Broadcast
				sendBroadcast(mIntent);
			}
		});
	}
	
	/*private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals("MY_ACTION_NAME")) {
				String message = intent.getExtras().getString("BroadCastData");
				Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
			}
		}
	};

	public void registerBoradcastReceiver() {
		IntentFilter myIntentFilter = new IntentFilter();
		myIntentFilter.addAction("MY_ACTION_NAME");
		//register broadcast
		registerReceiver(mBroadcastReceiver, myIntentFilter);
	}*/
}
