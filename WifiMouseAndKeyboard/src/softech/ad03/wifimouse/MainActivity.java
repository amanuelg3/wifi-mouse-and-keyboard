package softech.ad03.wifimouse;

import softech.ad03.wifimouse.classes.WrappedMotionEvent;
import softech.ad03.wifimouse.mouse.event.One;
import softech.ad03.wifimouse.mouse.event.Two;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class MainActivity extends Activity {
	GestureDetector mmGestureDetector;
	ScaleGestureDetector scaleGestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Settings.init(this);
		One oneTapEvent = new One();
		Two twoEvent = new Two();
		mmGestureDetector = new GestureDetector(this, oneTapEvent);
		mmGestureDetector.setIsLongpressEnabled(false);
		mmGestureDetector.setOnDoubleTapListener(oneTapEvent);
		scaleGestureDetector = new ScaleGestureDetector(this, twoEvent);
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
		mmGestureDetector.onTouchEvent(ev);
		scaleGestureDetector.onTouchEvent(ev);
		// switch (action) {
		// case MotionEvent.ACTION_DOWN:
		//
		// break;
		//
		// default:
		// break;
		// }
		// Settings.pointerCount = WrappedMotionEvent.getPointerCount(ev);
		// Log.e("COUNT", "" + Settings.pointerCount);

		// switch (action & MotionEvent.ACTION_MASK) {
		// case MotionEvent.ACTION_DOWN:
		// Settings.pointerCount = 1;
		// Settings.lastTapTime = System.currentTimeMillis();
		// break;
		//
		// case MotionEvent.ACTION_POINTER_DOWN:
		// Settings.pointerCount = ev.getPointerCount();
		// Settings.lastTapTime = System.currentTimeMillis();
		// Settings.yHistory = ev.getY();
		// break;
		// case MotionEvent.ACTION_POINTER_UP:
		// Log.e("E", "UP");
		// if (ev.getPointerCount() == 0) {
		// DupClick();
		// }
		// }

		// if (Settings.pointerCount == 1)
		// return mmGestureDetector.onTouchEvent(ev);

		return true;
	}

	public void DupClick() {
		if (Settings.pointerCount == 2) {
			Log.e("Click", "Dup");
		}
	}

}
