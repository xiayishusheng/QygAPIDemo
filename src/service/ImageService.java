package service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageService {

	/**
	 * get image
	 * @param pathStr
	 * @return Bitmap
	 * @throws Exception
	 */
	public static Bitmap getImage(String pathStr) throws Exception {
		Bitmap bitmap;
		URL url = new URL(pathStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("GET");
		if(200 == connection.getResponseCode()) {
			InputStream inputStream = connection.getInputStream();
			
			// method one
			bitmap = BitmapFactory.decodeStream(inputStream);
			
			// method two
			/*byte[] data = StreamTool.read(inputStream);
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);*/
			
			return bitmap;
		}
		return null;
	}

}
