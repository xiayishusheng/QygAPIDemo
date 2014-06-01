package qyg.apidemo.wifi;

import java.util.List;

import qyg.apidemo.R;
import android.app.Activity;
import android.os.Bundle;
import android.net.wifi.ScanResult;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WifiActivity extends Activity {
	
	/** define widget */
	private TextView zInfoTv;
	private Button zScan;
	private Button zOpen;
	private Button zClose;
	private Button zStatus;
	private WifiAdmin zWifiAdmin;

	private List<ScanResult> zResultList;
	private ScanResult zScanResult;
	private StringBuffer zStringBuffer = new StringBuffer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifi);
		zWifiAdmin = new WifiAdmin(WifiActivity.this);
		init();
	}

	public void init() {
		zInfoTv = (TextView) findViewById(R.id.id_wifi__info_tv);
		zScan = (Button) findViewById(R.id.id_wifi__scan_btn);
		zOpen = (Button) findViewById(R.id.id_wifi__open_btn);
		zClose = (Button) findViewById(R.id.id_wifi__close_btn);
		zStatus = (Button) findViewById(R.id.id_wifi__status_btn);
		zScan.setOnClickListener(new MyListener());
		zOpen.setOnClickListener(new MyListener());
		zClose.setOnClickListener(new MyListener());
		zStatus.setOnClickListener(new MyListener());
	}

	private class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.id_wifi__scan_btn:
				getAllNetWorkList();
				break;
			case R.id.id_wifi__open_btn:
				zWifiAdmin.openWifi();
				Toast.makeText(WifiActivity.this,
						"status:" + zWifiAdmin.checkState(), Toast.LENGTH_SHORT).show();
				break;
			case R.id.id_wifi__close_btn:
				zWifiAdmin.closeWifi();
				Toast.makeText(WifiActivity.this,
						"status:" + zWifiAdmin.checkState(), Toast.LENGTH_SHORT).show();
				break;
			case R.id.id_wifi__status_btn:
				Toast.makeText(WifiActivity.this,
						"status:" + zWifiAdmin.checkState(), Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}

	}

	public void getAllNetWorkList() {
		//clean result
		if (zStringBuffer != null) {
			zStringBuffer = new StringBuffer();
		}

		zWifiAdmin.startScan();
		zResultList = zWifiAdmin.getWifiList();
		if (zResultList != null) {
			for (int i = 0; i < zResultList.size(); i++) {
				zScanResult = zResultList.get(i);
				zStringBuffer = zStringBuffer.append(zScanResult.BSSID + "  ")
						.append(zScanResult.SSID + "   ")
						.append(zScanResult.capabilities + "   ")
						.append(zScanResult.frequency + "   ")
						.append(zScanResult.level + "\n\n");
			}
			zInfoTv.setText("scan result:\n" + zStringBuffer.toString());
		}
	}
}
