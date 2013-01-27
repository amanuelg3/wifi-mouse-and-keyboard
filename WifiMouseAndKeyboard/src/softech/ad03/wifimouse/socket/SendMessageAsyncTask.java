package softech.ad03.wifimouse.socket;

import java.io.IOException;

import android.os.AsyncTask;

public class SendMessageAsyncTask extends AsyncTask<String, Void, Void> {
	private Sender sender;
	public SendMessageAsyncTask(Sender sender) {
		this.sender = sender;
	}
	@Override
	protected Void doInBackground(String... strs) {
		// TODO Auto-generated method stub
		String str = strs[0];
		try {
			sender.sendAway(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
