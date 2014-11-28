package qyg.apidemo.xmlparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.xmlpull.v1.XmlSerializer;

import utils.Utils;
import android.os.Environment;
import android.util.Xml;
import entity.SmsInfo;

/**
 * 通过Xml的序列化来讲解SharedPreferences的实现
 * @author qyg
 *
 */
public class BackupSms {

	private List<SmsInfo> smsInfos;
	
	public BackupSms() {
		smsInfos = new ArrayList<SmsInfo>();
		Random random = new Random();
		long number = 13524289827L;
		for(int i=0; i<10; i++) {
			smsInfos.add(new SmsInfo(System.currentTimeMillis(), random.nextInt(2)+1,
					//"<短信内容"+i, Long.toString(number+i), i));//带有'<'符号，第一种方法就出问题了
					"短信内容"+i, Long.toString(number+i), i));
		}
	}

	/**
	 * 模拟备份手机短信
	 * <smss>
	 *     <sms>
	 *         <body>xxx</body>
	 *         <date>xxx</date>
	 *         <type>2</type>
	 *         <address>xxx</address>
	 *     </ssm>
	 * </smss>
	 */
	public void backSms() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<smss>");
		for(SmsInfo info : smsInfos) {
			sb.append("<sms>");
			
			sb.append("<address>");
			sb.append(info.getAddress());
			sb.append("</address>");
			
			sb.append("<type>");
			sb.append(info.getType());
			sb.append("</type>");
			
			sb.append("<body>");
			sb.append(info.getBody());
			sb.append("</body>");
			
			sb.append("<date>");
			sb.append(info.getDate());
			sb.append("</date>");
			
			sb.append("</sms>");
		}
		sb.append("</smss>");
		
		try {
			File file = new File(Environment.getExternalStorageDirectory(), "sms_backup.xml");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(sb.toString().getBytes());
			fos.close();
			Utils.logd("BackupSms", "backup ok!");
		} catch (IOException e) {
			e.printStackTrace();
			Utils.loge("BackupSms", "backup failed!");
		}
	}
	
	/**
	 * 使用 Xml serializer 模拟备份手机短信
	 * <smss>
	 *     <sms id="1">
	 *         <body>xxx</body>
	 *         <date>xxx</date>
	 *         <type>2</type>
	 *         <address>xxx</address>
	 *     </ssm>
	 * </smss>
	 */
	public void backSms2() {
		try {
			XmlSerializer serializer = Xml.newSerializer();
			File file = new File(Environment.getExternalStorageDirectory(), "sms_backup2.xml");
			FileOutputStream fos = new FileOutputStream(file);
			//初始化Xml序列化器的输出流和编码格式
			serializer.setOutput(fos, "utf-8");
			serializer.startDocument("utf-8", true);
			serializer.startTag(null, "smss");

			for(SmsInfo info : smsInfos) {
				serializer.startTag(null, "sms");
				serializer.attribute(null, "id", String.valueOf(info.getId()));
				
				serializer.startTag(null, "body");
				serializer.text(info.getBody());
				serializer.endTag(null, "body");
				
				serializer.startTag(null, "address");
				serializer.text(info.getAddress());
				serializer.endTag(null, "address");
				
				serializer.startTag(null, "type");
				serializer.text(info.getType() + "");
				serializer.endTag(null, "type");
				
				serializer.startTag(null, "date");
				serializer.text(info.getDate() + "");
				serializer.endTag(null, "date");
				
				serializer.endTag(null, "sms");
			}
			serializer.endTag(null, "smss");
			serializer.endDocument();
			
			fos.close();
			Utils.logd("BackupSms", "backup ok!");
		} catch (Exception e) {
			Utils.loge("BackupSms", "backup failed!");
		}
	}
	
}
