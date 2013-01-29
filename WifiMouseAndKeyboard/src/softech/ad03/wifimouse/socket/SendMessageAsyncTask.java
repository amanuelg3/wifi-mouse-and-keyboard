package softech.ad03.wifimouse.socket;

import java.io.IOException;

import android.os.AsyncTask;

public class SendMessageAsyncTask extends AsyncTask<String, Void, Void> {
	private Sender sender;
	private String splitChar = "|";
	private String msg;
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		msg = splitChar;
	}
	public SendMessageAsyncTask(Sender sender) {
		this.sender = sender;
	}
	@Override
	protected Void doInBackground(String... strs) {
		// TODO Auto-generated method stub
		for (String s:strs) {
			msg += s + splitChar;
		}
		try {
			sender.sendAway(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
