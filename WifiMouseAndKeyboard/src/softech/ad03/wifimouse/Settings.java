package softech.ad03.wifimouse;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

@SuppressLint("DefaultLocale")
public class Settings {
	public static String ip;
	public static int port;
	public static int clickTime;
	public static int sensitivity; // Độ nhạy khi di chuyển chuột
	public static int scrollSensitivity; // Độ nhạy khi scroll
	private static SharedPreferences prefs; // SharedPreferences dùng để lưu trữ thiết lập người dùng
	private static final String PREFS_IPKEY = "remoteip";
	private static final String PREFS_NAME = "wifimouse";
	private static final String PREFS_SENSITIVITY = "sensitivity";
	private static final String PREFS_SCROLL_SENSITIVITY = "scrollSensitivity";
	public static final int DEFAULT_PORT = 7777; // Port mặc định khi dò tìm server tự động
	
	
	public static final String LEFT_MOUSE_CLICK = "1";
	public static final String LEFT_MOUSE_DOWN = "2";
	public static final String LEFT_MOUSE_UP = "3";
	public static final String RIGHT_MOUSE_CLICK = "4";
	public static final String RIGHT_MOUSE_DOWN = "5";
	public static final String RIGHT_MOUSE_UP = "6";
	public static final String MOUSE_MOVE = "7";
    public static final String MOUSE_DOUBLE_CLICK = "8";
    public static final String MOUSE_WHEEL = "9";
	
	
	private static Context context;
	public static float xHistory, yHistory, xMove, yMove;
	public static long lastTapTime, last2TapTime;
	public static int pointerCount;
	public static float xWheelHistory, yWheelHistory;
	public static int wheelCount = 0;

	public static void init(Context context) {
		Settings.context = context;
		if (prefs == null) {
			prefs = context.getSharedPreferences(PREFS_NAME,
					Context.MODE_PRIVATE);
			
			// Lấy cấu hình người dùng ra
			ip = prefs.getString(PREFS_IPKEY, "192.168.1.104");
			sensitivity = prefs.getInt(Settings.PREFS_SENSITIVITY, 0);
			scrollSensitivity = prefs.getInt(Settings.PREFS_SCROLL_SENSITIVITY, 50);
		}
	}
	
	
	public static void setIpAddress(String ip) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putString(PREFS_IPKEY, ip);
		edit.commit();
		Settings.ip = ip;
	}
	
	public static void setSensitivity(int sensitivity) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putInt(PREFS_SENSITIVITY, sensitivity);
		edit.commit();
		Settings.sensitivity = sensitivity;
	}
	
	public static void setScrollSensitivity(int sensitivity) {
		SharedPreferences.Editor edit = prefs.edit();
		edit.putInt(PREFS_SCROLL_SENSITIVITY, sensitivity);
		edit.commit();
		Settings.scrollSensitivity = sensitivity;
	}
	
	//Lấy địa chỉ ip wifi của thiết bị
	public static String getLocalIpAddress() {
		WifiManager myWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	      
	       WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
	       int myIp = myWifiInfo.getIpAddress();
	       //int intMyIp3 = myIp/0x1000000;
	       int intMyIp3mod = myIp%0x1000000;
	      
	       int intMyIp2 = intMyIp3mod/0x10000;
	       int intMyIp2mod = intMyIp3mod%0x10000;
	      
	       int intMyIp1 = intMyIp2mod/0x100;
	       int intMyIp0 = intMyIp2mod%0x100;
	       return String.format("%d.%d.%d", intMyIp0, intMyIp1, intMyIp2);
	}
}
