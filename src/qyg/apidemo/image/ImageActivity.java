package qyg.apidemo.image;

import qyg.apidemo.R;
import service.ImageService;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageActivity extends Activity {

	private String TAG = getClass().getSimpleName();
	
	/** 获取控件 */
	private EditText pathText;
	private ImageView imageView;
	//private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		pathText = (EditText) findViewById(R.id.editText1);
		imageView = (ImageView) findViewById(R.id.imageView1);
		/*button = (Button) findViewById(R.id.button1);	//此处按钮的监听事件是在xml布局文件中指定的
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showImage();
			}
		});*/
	}

	// 定义Handler对象
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		// 当有消息发送出来的时候就执行Handler的这个方法
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				Toast.makeText(getApplicationContext(), R.string.get_image_error, Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Bitmap bitmap = msg.getData().getParcelable("IMG");
				if(null != bitmap) {
					imageView.setImageBitmap(bitmap);
				} else {
					Toast.makeText(getApplicationContext(), "图片不存在！", Toast.LENGTH_SHORT).show();
				}
				break;

			default:
				break;
			}
		}
	};

	public void showImage(View v) {
		final String pathStr = pathText.getText().toString();
		new Thread() {
			public void run() {
				Bitmap bitmap = null;
				try {
					bitmap = ImageService.getImage(pathStr);
				} catch (Exception e) {
					Log.e(TAG, e.toString());
					handler.sendEmptyMessage(0);
				}
				//handler.sendEmptyMessage(1);
				Message msg = new Message();
				Bundle msgBundle = new Bundle();
				msgBundle.putParcelable("IMG", bitmap);
				msg.what = 1;
				msg.setData(msgBundle);
				handler.sendMessage(msg);
			}
		}.start();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
