package qyg.apidemo.settings;

import qyg.apidemo.R;
import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.widget.Toast;

public class Settings extends PreferenceActivity {

	private SwitchPreference wifiLogSwitchPreference;
	private SwitchPreference btLogSwitchPreference;
	private SwitchPreference gnssLogSwitchPreference;
	private SwitchPreference systemLogSwitchPreference;
	private Context mContext;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		mContext = this;
		wifiLogSwitchPreference = (SwitchPreference) findPreference("wifi_log_switch");
		btLogSwitchPreference = (SwitchPreference) findPreference("bt_log_switch");
		gnssLogSwitchPreference = (SwitchPreference) findPreference("gnss_log_switch");
		systemLogSwitchPreference = (SwitchPreference) findPreference("system_log_switch");
		setListener();
	}

	private void setListener() {
		wifiLogSwitchPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				boolean isChecked = wifiLogSwitchPreference.isChecked();
				if(isChecked) {
					wifiLogSwitchPreference.setChecked(false);
				} else {
					wifiLogSwitchPreference.setChecked(true);
				}
				Toast.makeText(mContext, "" + wifiLogSwitchPreference.isChecked(), Toast.LENGTH_SHORT).show();
				
				return false;
			}
		});
		btLogSwitchPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				boolean isChecked = btLogSwitchPreference.isChecked();
				if(isChecked) {
					btLogSwitchPreference.setChecked(false);
				} else {
					btLogSwitchPreference.setChecked(true);
				}
				Toast.makeText(mContext, "" + btLogSwitchPreference.isChecked(), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		gnssLogSwitchPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				boolean isChecked = gnssLogSwitchPreference.isChecked();
				if(isChecked) {
					gnssLogSwitchPreference.setChecked(false);
				} else {
					gnssLogSwitchPreference.setChecked(true);
				}
				Toast.makeText(mContext, "" + gnssLogSwitchPreference.isChecked(), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		systemLogSwitchPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				boolean isChecked = systemLogSwitchPreference.isChecked();
				if(isChecked) {
					systemLogSwitchPreference.setChecked(false);
				} else {
					systemLogSwitchPreference.setChecked(true);
				}
				Toast.makeText(mContext, "" + systemLogSwitchPreference.isChecked(), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}
	
}
