package qyg.apidemo.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationView extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText("Here are the details for the notification...");
		setContentView(textView);

		// ---look up the notification manager service---
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// ---cancel the notification that we started---
		nm.cancel(getIntent().getExtras().getInt("notificationID"));
	}
}
