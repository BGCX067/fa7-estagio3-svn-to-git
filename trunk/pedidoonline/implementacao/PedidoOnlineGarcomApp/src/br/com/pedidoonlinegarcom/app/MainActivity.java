package br.com.pedidoonlinegarcom.app;

import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import br.com.pedidoonlinegarcomapp.R;

import com.parse.ParseAnalytics;
import com.parse.PushService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ParseAnalytics.trackAppOpened(getIntent());

		Set<String> setOfAllSubscriptions = PushService.getSubscriptions(this);
		
		TextView textView = (TextView) findViewById(R.id.textView);
		textView.setText(setOfAllSubscriptions.toString());			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
