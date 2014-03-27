package qyg.apidemo.video;

import qyg.apidemo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class VideoActivity extends Activity {

	/** »ñÈ¡¿Ø¼þ */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_video);s
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
