package softech.ad03.wifimouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class MainActivity extends Activity {
	private float xHistory, yHistory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Settings.init(this);
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
		float xMove = 0f;
		float yMove = 0f;
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xHistory = ev.getX();
			yHistory = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:

		default:
			break;
		}

		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			xMove = ev.getX() - xHistory;
			yMove = ev.getY() - yHistory;
			xHistory = ev.getX();
			yHistory = ev.getY();
			Log.e("M", "" + xMove + "x" + yMove);
		}
		return true;
	}

}
