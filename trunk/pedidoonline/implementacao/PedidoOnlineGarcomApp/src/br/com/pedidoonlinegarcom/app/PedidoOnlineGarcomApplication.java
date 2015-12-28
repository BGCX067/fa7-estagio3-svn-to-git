package br.com.pedidoonlinegarcom.app;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class PedidoOnlineGarcomApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "1xBLpkMNncOPTk0OMkNECsSYiFGmkouHXiAR1n3T",
				"ZN9TE6sQnJSYD3YEK2D56Nef53zcq76bfmNK8wLL");

		PushService.setDefaultPushCallback(this, PushActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();

		PushService.subscribe(this, "Garcom", MainActivity.class);
	}

}
