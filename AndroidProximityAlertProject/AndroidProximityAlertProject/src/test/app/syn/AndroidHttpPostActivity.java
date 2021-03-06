package test.app.syn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidHttpPostActivity extends Activity {

	TextView result;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_http_post);
		result = (TextView) findViewById(R.id.result);

		BufferedReader bufferedReader = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost request = new HttpPost("http://search.yahoo.com/search");
		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("p", "Android"));

		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
					postParameters);
			request.setEntity(entity);

			HttpResponse response = httpClient.execute(request);

			bufferedReader = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LineSeparator = System.getProperty("line.separator");
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line + LineSeparator);
			}
			bufferedReader.close();

			result.setText(stringBuffer.toString());

			Toast.makeText(AndroidHttpPostActivity.this, "Finished",
					Toast.LENGTH_LONG).show();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(AndroidHttpPostActivity.this, e.toString(),
					Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(AndroidHttpPostActivity.this, e.toString(),
					Toast.LENGTH_LONG).show();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}