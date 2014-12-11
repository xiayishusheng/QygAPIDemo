package utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

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
	
	/**
	 * 写信息到文件
	 * @param context 上下文
	 * @param info 要保存的信息
	 * @return true:保存成功，false:保存失败
	 */
	public static boolean saveInfo(Context context, String info) {
		try {
			File file = new File(context.getFilesDir(), "info.txt");
			FileOutputStream fos = new FileOutputStream(file);
			//fos = context.openFileOutput("info.txt", Context.MODE_PRIVATE);
			fos.write(info.getBytes());
			fos.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 读取信息
	 * @param context 上下文
	 * @return 信息
	 */
	public static String readInfo(Context context) {
		String result = "";
		try {
			File file = new File(context.getFilesDir(), "info.txt");
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			result = br.readLine();
			br.close();
		} catch (Exception e) {
			loge("readInfo", "error");
		}
		return result;
	}
	
	/**
	 * 检查sdcard是否可用
	 * @param context 上下文
	 * @return true:可用，false:不可用
	 */
	public static boolean getSdcardState(Context context) {
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			return true;
		} else {
			makeToast(context, "Sdcard不可用!");
			return false;
		}
	}
	
	/**
	 * 显示Toast
	 * @param context 上下文
	 * @param msg 显示的信息
	 */
	public static void makeToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 获取Sdcard的容量大小
	 * @param context 上下文
	 * @return Sdcard的容量大小
	 */
	public static String getSdcardSize(Context context) {
		File path = Environment.getExternalStorageDirectory();
		//File path = Environment.getDataDirectory();//data
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSizeLong();
		long totalBlocks = stat.getBlockCountLong();
		long availableBlocks = stat.getAvailableBlocksLong();
		
		long totalSize = blockSize * totalBlocks;
		long availSize = blockSize * availableBlocks;
		
		String totalStr = Formatter.formatFileSize(context, totalSize);//总大小
		String availStr = Formatter.formatFileSize(context, availSize);//可用大小
		return totalStr;
	}
	
	/**
	 * 获取root权限，并以root身份执行命令
	 */
	public static void getRoot() {

		DataOutputStream os = null;
	    DataInputStream is = null;
	    Process process = null;
		try {
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			is = new DataInputStream(process.getInputStream());
			os.writeBytes("netcfg wlan0 up" + " \n"); //这里可以执行具有root 权限的程序了  
            os.writeBytes(" exit \n");
            os.flush();
            process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
                process.destroy();
            } catch (Exception e) {
            	e.printStackTrace();
            }
		}
	}
	
}
