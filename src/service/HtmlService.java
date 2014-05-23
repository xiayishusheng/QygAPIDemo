package service;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import utils.StreamTool;
import android.util.Log;

public class HtmlService {

	private final String TAG = getClass().getSimpleName();
	
	public String getHtml(String path) throws Exception {
		// 打开一个基于传入URL路径字符串的HttpURLConnection
		HttpsURLConnection coon = (HttpsURLConnection) new URL(path).openConnection();
		coon.setConnectTimeout(5000);
		coon.setRequestMethod("GET");
		if(200 == coon.getResponseCode()) {
			InputStream inputStream = coon.getInputStream();
			byte[] data = StreamTool.read(inputStream);
			Log.i(TAG, coon.getResponseCode() + "");
			return new String(data);
		}
		Log.i(TAG, "传输成功！");
		return path;
	}
}
