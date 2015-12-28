package br.com.pedidoonline.app;

import jim.h.common.android.lib.zxing.config.ZXingLibConfig;
import jim.h.common.android.lib.zxing.integrator.IntentIntegrator;
import jim.h.common.android.lib.zxing.integrator.IntentResult;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import br.com.pedidoonline.app.service.PedidoClient;

import com.parse.ParseAnalytics;

public class MainActivity extends Activity {

	private ZXingLibConfig zxingLibConfig;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		zxingLibConfig = new ZXingLibConfig();
		zxingLibConfig.useFrontLight = true;
		
		ParseAnalytics.trackAppOpened(getIntent());

	}

	public void onClickQrcode(View view) {
		IntentIntegrator.initiateScan(this, zxingLibConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void OnClickCardapio(View view) {

		startActivity(new Intent(this, CardapioActivity.class));

	}

	public void OnClickMeusPedidos(View view) {

		startActivity(new Intent(this, PedidoListActivity.class));

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case IntentIntegrator.REQUEST_CODE:
			IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
			if (scanResult == null) {
				return;
			}
			final String result = scanResult.getContents();
			if (result != null) {
				String[] urls = result.split(";");
				PedidoClient.getInstace().registrarUrls(urls);
			}
			break;
		default:
		}
	}

}
