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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asynctask);
		
		/** 获取控件 */
		TextView progressTextView = (TextView) findViewById(R.id.progress);
		DownloadFilesTask downloadFilesTask = new DownloadFilesTask(progressTextView);
		downloadFilesTask.execute(new URL[]{});
		//downloadFilesTask.cancel(true);
	}

	/**
	 * 异步下载任务，处理耗时工作同时负责与主线程的通信
	 * @author YangZhen
	 */
	private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

		private TextView textView;
		public DownloadFilesTask(TextView progressTextView) {
			//super();
			this.textView = progressTextView;
		}

		/**
		 * 第一被调用的方法
		 * 任务执行前会被调用，适合于做初始化工作
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		/**
		 * 必须被实现的方法
		 * 第二被调用的方法
		 * 执行具体的耗时工作
		 */
		@Override
		protected Long doInBackground(URL... urls) {
			//int count = urls.length;
			int count = 30;
			long totalSize = 0;
			for (int i = 0; i <= count; i++) {
				/*totalSize += Downloader.downloadFile(urls[i]);
				publishProgress((int) ((i / (float) count) * 100));*/
				// Escape early if cancel() is called
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
		 * 第三被执行的方法
		 * 显示正在执行任务的进度
		 */
		@Override
		protected void onProgressUpdate(Integer... progress) {
			//setProgressPercent(progress[0]);
			Log.i(TAG, "当前进度为：" + progress[0]);
			textView.setText("当前进度为：" + progress[0]);
		}

		/**
		 * 第四被调用的方法
		 * 任务执行完成时被调用
		 */
		@Override
		protected void onPostExecute(Long result) {
			Toast.makeText(getApplicationContext(), "下载完毕！", Toast.LENGTH_SHORT).show();
		}

		/**
		 * 当任务被取消时会被调用，当调用此方法时，系统不会再调用onPostExecute
		 */
		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

	}
	
}
