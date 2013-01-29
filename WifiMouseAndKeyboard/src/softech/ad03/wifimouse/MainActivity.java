package softech.ad03.wifimouse;

import java.net.Socket;

import softech.ad03.wifimouse.mouse.event.One;
import softech.ad03.wifimouse.mouse.event.Two;
import softech.ad03.wifimouse.socket.Receiver;
import softech.ad03.wifimouse.socket.SendMessageAsyncTask;
import softech.ad03.wifimouse.socket.Sender;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends Activity {
	GestureDetector mmGestureDetector;
	ScaleGestureDetector scaleGestureDetector;
	private Socket theSocket;
	private Receiver receiver;
	private Sender sender;
	private InputMethodManager imm;
	private boolean isKeyboardShowed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Settings.init(this);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		Intent i = getIntent();
		if (i.getStringExtra("remote ip") != null) {
			try {
				String ip = i.getStringExtra("remote ip");
				int port = i.getIntExtra("remote port", 7777);
				theSocket = new Socket(ip, port);
				sender = new Sender(theSocket); // Creates an instance of class
												// // Sender
				receiver = new Receiver(this, theSocket); // Creates an instance
															// of // class
															// Receiver
				Log.e("S", "Connected to server");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e("ER", "LOI");
				e.printStackTrace();
			}

			One oneTapEvent = new One(sender);
			Two twoEvent = new Two(sender);
			mmGestureDetector = new GestureDetector(this, oneTapEvent);
			mmGestureDetector.setIsLongpressEnabled(false);
			mmGestureDetector.setOnDoubleTapListener(oneTapEvent);
			scaleGestureDetector = new ScaleGestureDetector(this, twoEvent);
		}

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();
		int keyCode = event.getKeyCode();
		Log.e("K", "" + keyCode);
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_UP:
			if (action == KeyEvent.ACTION_DOWN) {
				// TODO
				if (sender != null)
					new SendMessageAsyncTask(sender)
							.execute(Settings.VOLUME_UP);
			}
			return true;
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			if (action == KeyEvent.ACTION_DOWN) {
				// TODO
				if (sender != null)
					new SendMessageAsyncTask(sender)
							.execute(Settings.VOLUME_DOWN);
			}
			return true;
		default:
			return super.dispatchKeyEvent(event);
		}
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
		return true;
	}

	public void showHideSoftKeyboard(View v) {
		isKeyboardShowed = !isKeyboardShowed;
		if (isKeyboardShowed) {
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 1);
			Log.e("Keyboard", "Show Keyboard");
		} else {
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
			Log.e("Keyboard", "Hide Keyboard");
		}
	}
}
