package softech.ad03.wifimouse.classes;

import java.io.IOException;
import java.io.OutputStream;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class MyXMLBuilder {
	public static String LeftClick(float x, float y) {
		OutputStream os = new OutputStream() {

			private StringBuilder str = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.str.append((char) b);
			}

			// Netbeans IDE automatically overrides this toString()
			public String toString() {
				return this.str.toString();
			}
		};
		XmlSerializer serializer = Xml.newSerializer();
		try {
			serializer.setOutput(os, "UTF-8");
			serializer.startDocument("UTF-8", Boolean.valueOf(true));
			serializer.setFeature(
					"http://xmlpull.org/v1/doc/features.html#indent-output",
					true);
			serializer.startTag(null, "root");
			
			serializer.startTag(null, "method");
			serializer.text("click");
			serializer.endTag(null, "method");
			
			serializer.startTag(null, "x");
			serializer.text(String.valueOf(x));
			serializer.endTag(null, "x");
			
			serializer.startTag(null, "y");
			serializer.text(String.valueOf(y));
			serializer.endTag(null, "y");
			
			serializer.endTag(null, "root");
			serializer.endDocument();
			serializer.flush();
			os.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return os.toString();
	}
}
