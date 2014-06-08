package qyg.apidemo.actionbar;

import qyg.apidemo.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActionBarTest extends Activity {

	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(layoutResID);
		mContext = this;
		SpannableStringBuilder styleTitle = new SpannableStringBuilder(getTitle());
		styleTitle.setSpan(new ForegroundColorSpan(Color.BLACK), 0, getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_background_color));
		actionBar.setTitle(styleTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int menuId = item.getItemId();
		switch (menuId) {
		case android.R.id.home:
			finish();
			break;
		case R.id.action_menu_delete:
			Toast.makeText(mContext, "action_menu_delete", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_menu_folder:
			Toast.makeText(mContext, "action_menu_folder", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_menu_setting:
			Toast.makeText(mContext, "action_menu_setting", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
