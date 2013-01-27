package softech.ad03.wifimouse.mouse.event;

import softech.ad03.wifimouse.Settings;
import android.util.Log;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class One implements OnGestureListener, OnDoubleTapListener {

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.e("DOUBLE TAP", "MOUSE DOWN");
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		if (e.getAction() == MotionEvent.ACTION_MOVE)
			Log.e("DOUBLE TAP", "MOVE");
		else if (e.getAction() == MotionEvent.ACTION_UP)
			Log.e("DOUBLE TAP", "MOUSE UP");
		return true;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub

		Log.e("Mouse", "Left Click");
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		Settings.xHistory = e.getX();
		Settings.yHistory = e.getY();
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		Settings.xMove = distanceX - Settings.xHistory;
		Settings.yMove = distanceY - Settings.yHistory;
		Settings.xHistory = distanceX;
		Settings.yHistory = distanceY;
		Log.e("Mouse", "Move: " + Settings.xMove + "x" + Settings.yMove);
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return true;
	}

}
