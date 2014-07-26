package utils;

import android.util.Log;

public class Utils {

	public static void logn() {
		//do nothing
	}
	
	public static void logw(String tag, String msg) {
		Log.w(tag, msg);
	}
	
	public static void logd(String tag, String msg) {
		Log.d(tag, msg);
	}
	
	public static void logi(String tag, String msg) {
		Log.i(tag, msg);
	}
	
	public static void loge(String tag, String msg) {
		Log.e(tag, msg);
	}
}
