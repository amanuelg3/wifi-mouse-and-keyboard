package softech.ad03.wifimouse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ServerConfigActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server_config);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_server_config, menu);
		return true;
	}

}
