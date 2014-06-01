package qyg.apidemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();   
        String msg = bundle.getString("BroadCastData");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}   
}