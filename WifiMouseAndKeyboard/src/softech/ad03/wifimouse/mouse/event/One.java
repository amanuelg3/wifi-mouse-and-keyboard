package softech.ad03.wifimouse.mouse.event;

import softech.ad03.wifimouse.Settings;
import softech.ad03.wifimouse.socket.SendMessageAsyncTask;
import softech.ad03.wifimouse.socket.Sender;
import android.util.Log;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class One implements OnGestureListener, OnDoubleTapListener {
	private Sender sender;
	private int moveCount;
	private double tapTime;
	private int wheelCount;

	public One(Sender sender) {
		this.sender = sender;
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub
		tapTime = System.currentTimeMillis();
		wheelCount = 0;
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		if (e.getAction() == MotionEvent.ACTION_MOVE) {

			if (tapTime != 0 && System.currentTimeMillis() - tapTime > 200) {
				tapTime = 0;
				new SendMessageAsyncTask(sender).execute(Settings.LEFT_MOUSE_DOWN);
			}
				
			Settings.xMove = e.getX() - Settings.xHistory;
			Settings.yMove = e.getY() - Settings.yHistory;
			Settings.xHistory = e.getX();
			Settings.yHistory = e.getY();
			new SendMessageAsyncTask(sender).execute(Settings.MOUSE_MOVE,
					String.valueOf(Settings.xMove),
					String.valueOf(Settings.yMove));
		} else if (e.getAction() == MotionEvent.ACTION_UP)
			if (tapTime != 0 && System.currentTimeMillis() - tapTime <= 200)
				new SendMessageAsyncTask(sender)
						.execute(Settings.MOUSE_DOUBLE_CLICK);
			else
				new SendMessageAsyncTask(sender)
						.execute(Settings.LEFT_MOUSE_UP);
		return true;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		new SendMessageAsyncTask(sender).execute(Settings.LEFT_MOUSE_CLICK);
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		Settings.xHistory = e.getX();
		Settings.yHistory = e.getY();
		moveCount = 1;
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		Settings.xMove = e2.getX() - Settings.xHistory;
		Settings.yMove = e2.getY() - Settings.yHistory;
		Settings.xHistory = e2.getX();
		Settings.yHistory = e2.getY();
		if (moveCount != 1) {

			new SendMessageAsyncTask(sender).execute(Settings.MOUSE_MOVE,
					String.valueOf(Settings.xMove),
					String.valueOf(Settings.yMove));
		}
		moveCount++;
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
