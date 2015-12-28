package br.com.artssabores.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

//é preciso isolar dentro de uma thread para evitar o travamento da tela
public class ExportasCestasService extends Service implements Runnable {

	@Override
	public void onCreate() {
		new Thread(ExportasCestasService.this).start();
	}

	@Override
	public void run() {
		String uri = "http://192.168.25.15/inserir.php?cesta=cestaAndroid&preco=2.50&latitude=1313&longitude=23223";

		try {
			URL url = new URL(uri);
			HttpURLConnection http =  (HttpURLConnection) url.openConnection();
			InputStreamReader ips = new InputStreamReader(http.getInputStream());
			BufferedReader line = new BufferedReader(ips);

			String linhaRetorno = line.readLine();

			if (linhaRetorno.equals("Y")) {
				Log.d("TESTE", "Conectado");
			} else {
				Log.d("TESTE", "não conectado");
			}
			

		} catch (Exception e) {
			Log.d("EXEÇÃO", e.getMessage());
		}
		
		/*
		 * Caso de nototificcar o usuarioo que uma ação foi feita em background
		 * video aulas rlsystem
		 * 
		 NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		 
		 Notification nt = null;
		 
		 if(totalDB == totalReplicado){
			 nt = new Notification(R.drawable.ic_launcher, "Status Replicação", System.currentTimeMillis());
		 
			 nt.flags |= Notification.FLAG_AUTO_CANCEL;
			 
			 PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this.getApplicationContext(), MainActivity.class), 0);
			 
			 nt.setLatestEventInfo(this, "Status Replicação", "A replicação foi feita com sucesso, total: " + totalReplicado, p);
		 } else {
			 nt = new Notification(R.drawable.ic_launcher, "Status Replicação", System.currentTimeMillis());
			 
			 nt.flags |= Notification.FLAG_AUTO_CANCEL;
			 
			 PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this.getApplicationContext(), MainActivity.class), 0);
			 
			 nt.setLatestEventInfo(this, "Status Replicação", "A replicação não foi feita com sucesso, total: " + totalReplicado + " de " + totalDB, p);
		 }
		 
		nt.vibrate = new long[]{100, 2000, 1000, 2000}; 
		
		notificationManager.notify((int)Math.round(Math.random()), nt);
		*/
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
