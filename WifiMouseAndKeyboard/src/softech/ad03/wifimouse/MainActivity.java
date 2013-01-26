package softech.ad03.wifimouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.GestureDetector.OnDoubleTapListener;

public class MainActivity extends Activity implements
		android.view.GestureDetector.OnGestureListener, OnDoubleTapListener {
	private float xHistory, yHistory, xMove, yMove;
	GestureDetector mmGestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Settings.init(this);
		mmGestureDetector = new GestureDetector(this, this);
		mmGestureDetector.setIsLongpressEnabled(false);
		mmGestureDetector.setOnDoubleTapListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch (item.getItemId()) {

		case R.id.menu_servers:
			i = new Intent(MainActivity.this, ServerConfigActivity.class);
			startActivity(i);
			break;
		case R.id.menu_settings:
			i = new Intent(MainActivity.this, SettingsActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (ev.getPointerCount() == 1)
			return mmGestureDetector.onTouchEvent(ev);
		return true;
		// float xMove = 0f;
		// float yMove = 0f;
		// switch (ev.getAction()) {
		// case MotionEvent.ACTION_DOWN:
		// xHistory = ev.getX();
		// yHistory = ev.getY();
		// break;
		// case MotionEvent.ACTION_MOVE:
		//
		// default:
		// break;
		// }
		//
		// if (ev.getAction() == MotionEvent.ACTION_MOVE) {
		// xMove = ev.getX() - xHistory;
		// yMove = ev.getY() - yHistory;
		// xHistory = ev.getX();
		// yHistory = ev.getY();
		// Log.e("M", "" + xMove + "x" + yMove);
		// }
		// return true;
	}

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
		xHistory = e.getX();
		yHistory = e.getY();
		Log.e("A", "onDown");
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		Log.e("A", "onFling");
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.e("A", "onLongPress");

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		xMove = distanceX - xHistory;
		yMove = distanceY - yHistory;
		xHistory = distanceX;
		yHistory = distanceY;
		Log.e("Mouse", "Move: " + xMove + "x" + yMove);
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.e("A", "onShowPress");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.e("A", "onSingleTapUp");
		return true;
	}

}
