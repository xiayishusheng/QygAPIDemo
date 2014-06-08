package qyg.apidemo.drawable;

import qyg.apidemo.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ChangeDrawable extends Activity {

	private TextView textView;
	private ToggleButton toggleButton;
	
	private Context zContext;
	private Drawable writeDrawable;
	private Drawable wrongDrawable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changedrawable);
		zContext = this;
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME);
		writeDrawable = getResources().getDrawable(R.drawable.write);
		writeDrawable.setBounds(0, 0, writeDrawable.getMinimumWidth(), writeDrawable.getMinimumHeight());
		wrongDrawable = getResources().getDrawable(R.drawable.wrong);
		wrongDrawable.setBounds(0, 0, wrongDrawable.getMinimumWidth(), wrongDrawable.getMinimumHeight());
		textView = (TextView) findViewById(R.id.id_changedrawable__tv);
		toggleButton = (ToggleButton) findViewById(R.id.id_changedrawable__toggleButton);
		toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					textView.setCompoundDrawables(writeDrawable, null, null, null);
				} else {
					textView.setCompoundDrawables(wrongDrawable, null, null, null);
				}
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int menuId = item.getItemId();
		switch (menuId) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
