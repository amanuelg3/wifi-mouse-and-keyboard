package softech.ad03.wifimouse.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.util.Log;

public class Sender {
	private DataOutputStream out;

	public Sender(Socket theSocket) {

		try {
			out = new DataOutputStream(theSocket.getOutputStream());
		} catch (Exception e) {
			Log.e("Error", "Khong the tao Sender");
		}
	}

	/*
	 * Closes the outgoing stream
	 */
	public void closeOutStream() {

		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendAway(String s) throws IOException {

		if (out != null) {
			out.writeUTF(s);
			out.flush();
		}
	}
}
