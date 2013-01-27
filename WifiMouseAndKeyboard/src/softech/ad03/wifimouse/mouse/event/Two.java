package softech.ad03.wifimouse.mouse.event;

import softech.ad03.wifimouse.Settings;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

public class Two implements OnScaleGestureListener {

	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub
		if (System.currentTimeMillis() - Settings.last2TapTime > 200) {
			Settings.wheelCount++;
			float xWheel = detector.getFocusX() - Settings.xWheelHistory;
			float yWheel = detector.getFocusY() - Settings.yWheelHistory;
			Settings.xWheelHistory = detector.getFocusX();
			Settings.yWheelHistory = detector.getFocusY();
			Log.e("SCALE", "onScale " + yWheel);
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
		if (System.currentTimeMillis() - Settings.last2TapTime <= 200)
			Log.e("Click", "Right Click");
	}

}
