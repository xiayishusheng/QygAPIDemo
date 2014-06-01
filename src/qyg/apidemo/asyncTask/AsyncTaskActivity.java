package qyg.apidemo.asyncTask;

import java.net.URL;

import qyg.apidemo.R;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends Activity {

	private final String TAG = getClass().getSimpleName();

	/** define widget */
	private TextView zProgressTv;
	
	/** define variable */
	private DownloadFilesTask zDownloadFilesTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynctask);
		
		/** get widget */
		zProgressTv = (TextView) findViewById(R.id.id_asynctask_progress_tv);
		zDownloadFilesTask = new DownloadFilesTask(zProgressTv);
		zDownloadFilesTask.execute(new URL[]{});
		//downloadFilesTask.cancel(true);
	}

	/**
	 * async download task, handle the time-consuming work and communicate with the main thread
	 * @author YangZhen
	 */
	private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

		private TextView textView;
		public DownloadFilesTask(TextView progressTextView) {
			//super();
			textView = progressTextView;
		}

		/**
		 * First
		 * Before the tasks will be invoked, suitable for initialization.
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		/**
		 * Second
		 * Methods must be implemented
		 * Do the time-consuming work.
		 */
		@Override
		protected Long doInBackground(URL... urls) {
			//int count = urls.length;
			int count = 10;
			long totalSize = 0;
			for (int i = 0; i <= count; i++) {
				/*totalSize += Downloader.downloadFile(urls[i]);
				publishProgress((int) ((i / (float) count) * 100));*/
				//Escape early if cancel() is called
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i);
				if (isCancelled())
					break;
			}
			return totalSize;
		}

		/**
		 * Third
		 * Show the task being performed.
		 */
		@Override
		protected void onProgressUpdate(Integer... progress) {
			//setProgressPercent(progress[0]);
			Log.i(TAG, "Current Poocess : " + progress[0]);
			textView.setText("Current Poocess : " + progress[0]);
		}

		/**
		 * Fourth
		 * Called when the task is completed.
		 */
		@Override
		protected void onPostExecute(Long result) {
			Toast.makeText(getApplicationContext(), "The download is complete!", Toast.LENGTH_SHORT).show();
		}

		/**
		 * This method is called when the task is cancelled. The system will not call onPostExecute().
		 */
		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

	}
	
}
