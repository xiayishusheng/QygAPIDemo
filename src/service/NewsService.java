package service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.StreamTool;
import entity.News;

public class NewsService {

	/**
	 * 获取最新资讯
	 * @return 列表
	 * @throws Exception
	 */
	public static List<News> getJSONLastNews() throws Exception {
		String path = "http://192.168.1.139:8080/web/NewsListServlet?format=json";
		HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("GET");
		if(200 == connection.getResponseCode()) {
			InputStream inputStream = connection.getInputStream();
			return parseJSON(inputStream);
		}
		return null;
	}
	
	/**
	 * 解析JSON
	 * @param jsonInputStream
	 * @return
	 * @throws Exception
	 */
	private static List<News> parseJSON(InputStream jsonInputStream) throws Exception {
		List<News> list = new ArrayList<News>();
		byte[] data = StreamTool.read(jsonInputStream);
		String jsonStr = new String(data);
		JSONArray jsonArray = new JSONArray(jsonStr);//	[{id=1,name=news1,timeLength=60},{id=2,name=news2,timeLength=100},{},{}...]
		for(int i=0; i<jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			int id = jsonObject.getInt("id");
			String titleStr = jsonObject.getString("title");
			int timeLength = jsonObject.getInt("timeLength");
			list.add(new News(id, titleStr, timeLength));
		}
		return list;
		
	}
	
}
