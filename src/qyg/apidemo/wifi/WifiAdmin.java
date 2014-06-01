package qyg.apidemo.wifi;

import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

public class WifiAdmin {
	//Define WifiManager Object
	private WifiManager zWifiManager;
	//Define WifiInfo Object
	private WifiInfo zWifiInfo;
	//Scan the list of network connections
	private List<ScanResult> zWifiList;
	//The list of network connections
	private List<WifiConfiguration> zWifiConfigurations;
	private WifiLock zWifiLock;

	public WifiAdmin(Context context) {
		zWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		zWifiInfo = zWifiManager.getConnectionInfo();
	}

	//Open WiFi
	public void openWifi() {
		if (!zWifiManager.isWifiEnabled()) {
			zWifiManager.setWifiEnabled(true);
		}
	}

	//Close WiFi
	public void closeWifi() {
		if (zWifiManager.isWifiEnabled()) {
			zWifiManager.setWifiEnabled(false);
		}
	}

	//Check WiFi Status
	public int checkState() {
		return zWifiManager.getWifiState();
	}

	//Lock WiFi
	public void acquireWifiLock() {
		zWifiLock.acquire();
	}

	//Release WiFi
	public void releaseWifiLock() {
		if (zWifiLock.isHeld()) {
			zWifiLock.acquire();
		}
	}

	//Create WiFiLock
	public void createWifiLock() {
		zWifiLock = zWifiManager.createWifiLock("test");
	}

	//Get WiFi Configuration
	public List<WifiConfiguration> getConfiguration() {
		return zWifiConfigurations;
	}

	//Connect to the specified configured network
	public void connetionConfiguration(int index) {
		if (index > zWifiConfigurations.size()) {
			return;
		}
		zWifiManager.enableNetwork(zWifiConfigurations.get(index).networkId, true);
	}

	public void startScan() {
		zWifiManager.startScan();
		zWifiList = zWifiManager.getScanResults();
		zWifiConfigurations = zWifiManager.getConfiguredNetworks();
	}

	//Get Network List
	public List<ScanResult> getWifiList() {
		return zWifiList;
	}

	//Scan Result
	public StringBuffer lookUpScan() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < zWifiList.size(); i++) {
			sb.append("Index_" + String.valueOf(i + 1) + ":");
			//Convert ScanResult Info to String, include:BSSID,SSID,capabilities,frequency,level
			sb.append((zWifiList.get(i)).toString()).append("\n");
		}
		return sb;
	}

	public String getMacAddress() {
		return (zWifiInfo == null) ? "NULL" : zWifiInfo.getMacAddress();
	}

	public String getBSSID() {
		return (zWifiInfo == null) ? "NULL" : zWifiInfo.getBSSID();
	}

	public int getIpAddress() {
		return (zWifiInfo == null) ? 0 : zWifiInfo.getIpAddress();
	}

	//Get Connect Id
	public int getNetWordId() {
		return (zWifiInfo == null) ? 0 : zWifiInfo.getNetworkId();
	}

	//Get wifiInfo
	public String getWifiInfo() {
		return (zWifiInfo == null) ? "NULL" : zWifiInfo.toString();
	}

	//Add Network Confiuration
	public void addNetWork(WifiConfiguration configuration) {
		int wcgId = zWifiManager.addNetwork(configuration);
		zWifiManager.enableNetwork(wcgId, true);
	}

	//Disconnect specified id network
	public void disConnectionWifi(int netId) {
		zWifiManager.disableNetwork(netId);
		zWifiManager.disconnect();
	}
}