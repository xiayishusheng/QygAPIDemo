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
	/** Called when the activity is first created. */
	private TextView allNetWork;
	private Button scan;
	private Button start;
	private Button stop;
	private Button check;
	private WifiAdmin mWifiAdmin;
	// ɨ�����б�
	private List<ScanResult> list;
	private ScanResult mScanResult;
	private StringBuffer sb = new StringBuffer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi);
		mWifiAdmin = new WifiAdmin(WifiActivity.this);
		init();
	}

	public void init() {
		allNetWork = (TextView) findViewById(R.id.allNetWork);
		scan = (Button) findViewById(R.id.scan);
		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);
		check = (Button) findViewById(R.id.check);
		scan.setOnClickListener(new MyListener());
		start.setOnClickListener(new MyListener());
		stop.setOnClickListener(new MyListener());
		check.setOnClickListener(new MyListener());
	}

	private class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.scan:// ɨ������
				getAllNetWorkList();
				break;
			case R.id.start:// ��Wifi
				mWifiAdmin.openWifi();
				Toast.makeText(WifiActivity.this,
						"��ǰwifi״̬Ϊ��" + mWifiAdmin.checkState(), 1).show();
				break;
			case R.id.stop:// �ر�Wifi
				mWifiAdmin.closeWifi();
				Toast.makeText(WifiActivity.this,
						"��ǰwifi״̬Ϊ��" + mWifiAdmin.checkState(), 1).show();
				break;
			case R.id.check:// Wifi״̬
				Toast.makeText(WifiActivity.this,
						"��ǰwifi״̬Ϊ��" + mWifiAdmin.checkState(), 1).show();
				break;
			default:
				break;
			}
		}

	}

	public void getAllNetWorkList() {
		// ÿ�ε��ɨ��֮ǰ�����һ�ε�ɨ����
		if (sb != null) {
			sb = new StringBuffer();
		}
		// ��ʼɨ������
		mWifiAdmin.startScan();
		list = mWifiAdmin.getWifiList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				// �õ�ɨ����
				mScanResult = list.get(i);
				sb = sb.append(mScanResult.BSSID + "  ")
						.append(mScanResult.SSID + "   ")
						.append(mScanResult.capabilities + "   ")
						.append(mScanResult.frequency + "   ")
						.append(mScanResult.level + "\n\n");
			}
			allNetWork.setText("ɨ�赽��wifi���磺\n" + sb.toString());
		}
	}
}
