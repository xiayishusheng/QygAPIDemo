package utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {

	/**
	 * read data from stream
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] read(InputStream inputStream) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while (-1 != (len = inputStream.read(buffer))) {
			byteArrayOutputStream.write(buffer, 0, len);
		}
		inputStream.close();
		return byteArrayOutputStream.toByteArray();
	}

}
