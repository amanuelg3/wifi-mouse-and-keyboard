package softech.ad03.wifimouse.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import android.content.Context;

public class Receiver {
	private DataInputStream in;
	private Context context;
	public Receiver(Context context, Socket theSocket) {
		this.context = context;

		try {
			in = new DataInputStream(theSocket.getInputStream());
		} catch (IOException ioe) {
		}
	}
	public void closeInStream() {

		try {
			if (in != null) {
				in.close();
			}
		} catch (IOException iE) {
		}
	}
}
