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
		
		/** ��ȡ�ؼ� */
		TextView progressTextView = (TextView) findViewById(R.id.progress);
		DownloadFilesTask downloadFilesTask = new DownloadFilesTask(progressTextView);
		downloadFilesTask.execute(new URL[]{});
		//downloadFilesTask.cancel(true);
	}

	/**
	 * �첽�������񣬴����ʱ����ͬʱ���������̵߳�ͨ��
	 * @author YangZhen
	 */
	private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

		private TextView textView;
		public DownloadFilesTask(TextView progressTextView) {
			//super();
			this.textView = progressTextView;
		}

		/**
		 * ��һ�����õķ���
		 * ����ִ��ǰ�ᱻ���ã��ʺ�������ʼ������
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		/**
		 * ���뱻ʵ�ֵķ���
		 * �ڶ������õķ���
		 * ִ�о���ĺ�ʱ����
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
		 * ������ִ�еķ���
		 * ��ʾ����ִ������Ľ���
		 */
		@Override
		protected void onProgressUpdate(Integer... progress) {
			//setProgressPercent(progress[0]);
			Log.i(TAG, "��ǰ����Ϊ��" + progress[0]);
			textView.setText("��ǰ����Ϊ��" + progress[0]);
		}

		/**
		 * ���ı����õķ���
		 * ����ִ�����ʱ������
		 */
		@Override
		protected void onPostExecute(Long result) {
			Toast.makeText(getApplicationContext(), "������ϣ�", Toast.LENGTH_SHORT).show();
		}

		/**
		 * ������ȡ��ʱ�ᱻ���ã������ô˷���ʱ��ϵͳ�����ٵ���onPostExecute
		 */
		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

	}
	
}
