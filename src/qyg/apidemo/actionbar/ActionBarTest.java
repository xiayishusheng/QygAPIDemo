package qyg.apidemo.actionbar;

import qyg.apidemo.R;
import qyg.apidemo.settings.Settings;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActionBarTest extends Activity {

	private TextView timeTextView;
	private TextView wifiLogTextView;
	private TextView btLogTextView;
	private TextView gnssLogTextView;
	private TextView systemLogTextView;
	private LinearLayout wifiLinearLayout;
	private LinearLayout btLinearLayout;
	private LinearLayout gnssLinearLayout;
	private LinearLayout systemLinearLayout;
	private ToggleButton toggleButton;
	private Context mContext;
	private SharedPreferences sharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbartest);
		mContext = this;
		SpannableStringBuilder style = new SpannableStringBuilder(getTitle());
		style.setSpan(new ForegroundColorSpan(Color.BLACK), 0, getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_background_color));
		actionBar.setTitle(style);
		
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
		
		timeTextView = (TextView) findViewById(R.id.id_actionbartest__time);
		wifiLogTextView = (TextView) findViewById(R.id.id_actionbartest__wifilog_tv);
		btLogTextView = (TextView) findViewById(R.id.id_actionbartest__btlog_tv);
		gnssLogTextView = (TextView) findViewById(R.id.id_actionbartest__gnsslog_tv);
		systemLogTextView = (TextView) findViewById(R.id.id_actionbartest__systemlog_tv);
		wifiLinearLayout = (LinearLayout) findViewById(R.id.id_actionbartest__wifilayout);
		btLinearLayout = (LinearLayout) findViewById(R.id.id_actionbartest__btlayout);
		gnssLinearLayout = (LinearLayout) findViewById(R.id.id_actionbartest__gnsslayout);
		systemLinearLayout = (LinearLayout) findViewById(R.id.id_actionbartest__systemlayout);
		toggleButton = (ToggleButton) findViewById(R.id.id_actionbartest__startstop_toggle);
		toggleButton.setOnCheckedChangeListener(toggleListener);
	}

	private CompoundButton.OnCheckedChangeListener toggleListener = new CompoundButton.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if(isChecked) {
				if(sharedPreferences.getBoolean("wifi_log_switch", true)) {
					wifiLinearLayout.setBackgroundColor(getResources().getColor(R.color.lightgreen));
				}
				if(sharedPreferences.getBoolean("bt_log_switch", true)) {
					btLinearLayout.setBackgroundColor(getResources().getColor(R.color.lightgreen));
				}
				if(sharedPreferences.getBoolean("gnss_log_switch", true)) {
					gnssLinearLayout.setBackgroundColor(getResources().getColor(R.color.lightgreen));
				}
				if(sharedPreferences.getBoolean("system_log_switch", true)) {
					systemLinearLayout.setBackgroundColor(getResources().getColor(R.color.lightgreen));
				}
			} else {
				wifiLinearLayout.setBackgroundColor(getResources().getColor(R.color.gray));
				btLinearLayout.setBackgroundColor(getResources().getColor(R.color.gray));
				gnssLinearLayout.setBackgroundColor(getResources().getColor(R.color.gray));
				systemLinearLayout.setBackgroundColor(getResources().getColor(R.color.gray));
			}
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int menuId = item.getItemId();
		switch (menuId) {
		case android.R.id.home:
			finish();
			break;
		case R.id.action_menu_delete:
			Toast.makeText(mContext, "action_menu_delete", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_menu_folder:
			Toast.makeText(mContext, "action_menu_folder", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_menu_setting:
			//Toast.makeText(mContext, "action_menu_setting", Toast.LENGTH_SHORT).show();
			Intent settingsIntent = new Intent();
			settingsIntent.setClass(mContext, Settings.class);
			startActivity(settingsIntent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
