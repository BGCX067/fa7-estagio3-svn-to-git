package br.com.pedidoonlinegarcom.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import br.com.pedidoonlinegarcomapp.R;

public class PushActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_push);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.push, menu);
		return true;
	}

}
