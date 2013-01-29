package softech.ad03.wifimouse.mouse.event;

import softech.ad03.wifimouse.Settings;
import softech.ad03.wifimouse.socket.SendMessageAsyncTask;
import softech.ad03.wifimouse.socket.Sender;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

public class Two implements OnScaleGestureListener {
	private Sender sender;

	public Two(Sender sender) {
		this.sender = sender;
	}

	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub
		if (System.currentTimeMillis() - Settings.last2TapTime > 200) {
			Settings.wheelCount++;
			float xWheel = detector.getFocusX() - Settings.xWheelHistory;
			float yWheel = detector.getFocusY() - Settings.yWheelHistory;
			Settings.xWheelHistory = detector.getFocusX();
			Settings.yWheelHistory = detector.getFocusY();
			new SendMessageAsyncTask(sender).execute(Settings.MOUSE_WHEEL, String.valueOf(yWheel));
		}
		return true;
	}

	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub
		Settings.wheelCount = 0;
		Settings.xWheelHistory = detector.getFocusX();
		Settings.yWheelHistory = detector.getFocusY();
		Settings.last2TapTime = System.currentTimeMillis();
		return true;
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub
		Settings.wheelCount = 0;
		if (System.currentTimeMillis() - Settings.last2TapTime <= 200) {
			new SendMessageAsyncTask(sender).execute(Settings.RIGHT_MOUSE_CLICK);
		}
	}

}
