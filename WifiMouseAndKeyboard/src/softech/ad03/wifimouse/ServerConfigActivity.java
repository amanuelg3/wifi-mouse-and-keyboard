package softech.ad03.wifimouse;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ServerConfigActivity extends Activity {
	static private final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
	static private Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

	public static boolean isValidIPV4(String s) {
		return IPV4_PATTERN.matcher(s).matches();
	}

	private EditText etIp, etPort;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server_config);
		etIp = (EditText) findViewById(R.id.etServerIp);
		etPort = (EditText) findViewById(R.id.etServerPort);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_server_config, menu);
		return true;
	}

	public void mConnect(View v) {
		String ip = etIp.getText().toString().trim();
		String port = etPort.getText().toString().trim();
		if (!isValidIPV4(ip)) {
			Toast.makeText(getApplicationContext(), R.string.invalid_ip_address, Toast.LENGTH_SHORT).show();
			return;
		}
		if (port.length() < 3) {
			Toast.makeText(getApplicationContext(), R.string.invalid_port, Toast.LENGTH_SHORT).show();
			return;
		}
		Intent i = new Intent(ServerConfigActivity.this, MainActivity.class);
		i.putExtra("remote ip", ip);
		i.putExtra("remote port", port);
		startActivity(i);
	}

}
