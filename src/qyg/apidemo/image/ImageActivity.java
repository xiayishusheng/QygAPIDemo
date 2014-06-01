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
	
	/** define widget */
	private EditText zUrlPathEt;
	private ImageView zImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);
		zUrlPathEt = (EditText) findViewById(R.id.id_image__url_et);
		zImageView = (ImageView) findViewById(R.id.id_image__imageView);
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				Toast.makeText(getApplicationContext(), R.string.str_image__get_image_error, Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Bitmap bitmap = msg.getData().getParcelable("IMG");
				if(null != bitmap) {
					zImageView.setImageBitmap(bitmap);
				} else {
					Toast.makeText(getApplicationContext(), R.string.str_image__no_image, Toast.LENGTH_SHORT).show();
				}
				break;

			default:
				break;
			}
		}
	};

	public void showImage(View v) {
		final String pathStr = zUrlPathEt.getText().toString();
		new Thread() {
			public void run() {
				Bitmap bitmap = null;
				try {
					bitmap = ImageService.getImage(pathStr);
				} catch (Exception e) {
					Log.e(TAG, e.toString());
					handler.sendEmptyMessage(0);
				}
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
