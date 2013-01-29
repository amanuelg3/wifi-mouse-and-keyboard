package softech.ad03.wifimouse;

import java.net.Socket;

import softech.ad03.wifimouse.mouse.event.One;
import softech.ad03.wifimouse.mouse.event.Two;
import softech.ad03.wifimouse.socket.Receiver;
import softech.ad03.wifimouse.socket.Sender;
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
	private Socket theSocket;
	private Receiver receiver;
	private Sender sender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Settings.init(this);


		try {
			theSocket = new Socket("192.168.1.104", 7777);
			sender = new Sender(theSocket); // Creates an instance of class								// Sender
			receiver = new Receiver(this, theSocket); // Creates an instance of												// class Receiver
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
}
