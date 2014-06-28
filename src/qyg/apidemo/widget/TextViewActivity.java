package qyg.apidemo.widget;

import java.lang.reflect.Field;

import qyg.apidemo.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TextViewActivity extends Activity {

	private TextView textView1, textView2, textView3, textView4, textView5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textview);
		
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView4 = (TextView) findViewById(R.id.textView4);
		textView5 = (TextView) findViewById(R.id.textView5);
		
		//添加一段HTML标志
		String htmlStr = "<font color='red'>I like android</font><br>";
		htmlStr += "<font color='#0000ff'><big><i>I like android</i></big></font><p>";
		htmlStr += "<big><a href='http://www.baidu.com'>百度</a></big>";
		CharSequence charSequence = Html.fromHtml(htmlStr);
		textView1.setText(charSequence);
		textView1.setMovementMethod(LinkMovementMethod.getInstance());
		
		String texStr = "url  :http://www.sina.com\n";
		texStr += "email:test@163.com\n";
		texStr += "tel  :+86 010-54321843";
		textView2.setText(texStr);
		textView2.setMovementMethod(LinkMovementMethod.getInstance());
		
		String htmlStr3 = "图像1<img src='image1'/>" + "图像2<img src='image2'/>" + "图像3<img src='image3'/><p>";
		htmlStr3 += "图像4<a href='http://www.baidu.com'><img src='image4'></a>" + "图像5<img src='image5'/>";
		
		CharSequence charSequence3 = Html.fromHtml(htmlStr3, new Html.ImageGetter() {
			
			@Override
			public Drawable getDrawable(String source) {
				//获得系统资源的信息，比如图片信息
				Drawable drawable = getResources().getDrawable(getResourceId(source));
				//处理第三个图片文件，按照50%的比例进行压缩
				if(source.equals("image3")) {
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 3, drawable.getIntrinsicHeight() / 3);
				} else {
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2, drawable.getIntrinsicHeight() / 2);
				}
				return drawable;
			}
		}, null);
		textView3.setTextColor(Color.BLACK);
		//textView3.setBackgroundColor(Color.WHITE);
		textView3.setTextSize(20);
		textView3.setPadding(0, 50, 0, 0);
		textView3.setText(charSequence3);
		textView3.setMovementMethod(LinkMovementMethod.getInstance());
		
		final String text = "显示Toast";
		SpannableString spannableString = new SpannableString(text);
		spannableString.setSpan(new ClickableSpan() {
			@Override
			public void onClick(View widget) {
				Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
			}
		}, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView4.setText(spannableString);
		textView4.setMovementMethod(LinkMovementMethod.getInstance());
		
		String htmlStr5 = "早梅 <a href='http://www.baidu.com'>作者：张谓</a> 一树寒梅白玉条，迥临村路傍溪桥，不知近水花先发，疑是经冬雪未消。";
		textView5.setText(Html.fromHtml(htmlStr5));
		//textView5.setMovementMethod(LinkMovementMethod.getInstance());
	}

	public int getResourceId(String name) {
		try {
			//根据资源ID的变量名获得Field对象，使用反射机制来实现
			Field field = R.drawable.class.getField(name);
			//去的并返回资源的id字段（静态变量）的值，使用反射机制
			return Integer.parseInt(field.get(null).toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
}
