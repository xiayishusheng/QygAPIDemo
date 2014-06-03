package qyg.apidemo.bluetooth;

import qyg.apidemo.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BlueTooth extends Activity {

	private BluetoothAdapter mBluetoothAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth);
		// 得到BluetoothAdapter对象
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		// 判断BluetoothAdapter对象是否为空，如果为空，则表明本机没有蓝牙设备
		if (mBluetoothAdapter == null) {
			Toast.makeText(this, "没有找到蓝牙硬件或驱动！", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "本机拥有蓝牙设备！", Toast.LENGTH_SHORT).show();
		}
	}

	public void openBlueTooth(View view) {
		// 调用isEnabled()方法判断当前蓝牙设备是否可用
		if (mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()) {

			/*
			 * 如果蓝牙设备不可用的话,创建一个intent对象,提示用户启动蓝牙适配器
			 * 意图方式需要android.permission.BLUETOOTH权限
			 * Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			 * startActivity(intent);
			 */

			// 直接打开蓝牙需要android.permission.BLUETOOTH_ADMIN权限
			mBluetoothAdapter.enable();
		}

	}

	public void closeBlueTooth(View view) {
		mBluetoothAdapter.disable();// 关闭蓝牙
	}
}
