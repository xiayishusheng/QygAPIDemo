package qyg.apidemo.sqlite3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonActivity extends Activity implements View.OnClickListener {

	private Context mContext;
	private String strDBName;
	private int intDBVersion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		Button button = new Button(mContext);
		button.setOnClickListener(this);
		setContentView(button);
	}

	@Override
	public void onClick(View v) {
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(mContext, strDBName, null, intDBVersion);
		helper.getWritableDatabase();
	}
	
}
