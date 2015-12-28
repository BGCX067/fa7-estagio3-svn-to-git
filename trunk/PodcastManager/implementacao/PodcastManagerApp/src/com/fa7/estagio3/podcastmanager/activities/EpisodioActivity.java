package com.fa7.estagio3.podcastmanager.activities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FilenameUtils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ShareCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.fa7.estagio3.podcastmanager.entities.Episodio;
import com.fa7.estagio3.podcastmanager.repositorios.RepositorioEpisodio;
import com.fa7.estagio3.podcastmanagerapp.R;

public class EpisodioActivity extends Activity implements OnClickListener {

	private ShareActionProvider mShareActionProvider;
	public TextView startTimeField, endTimeField;
	private MediaPlayer mediaPlayer = null;
	private double startTime = 0;
	private double finalTime = 0;
	private Handler myHandler = new Handler();;
	private int forwardTime = 5000;
	private int backwardTime = 5000;
	private SeekBar seekbar;
	private ImageButton playButton, pauseButton;
	public static int oneTimeOnly = 0;
	private ProgressDialog dialog;
	public String fileUrl;
	private ProgressDialog pDialog;
	private Episodio episodio;
	private RepositorioEpisodio repositorioEpisodio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_episodio);

		Intent intentPodcast = getIntent();
		long episodioId = intentPodcast.getLongExtra("episodioId", 0);

		repositorioEpisodio = new RepositorioEpisodio(this);
		episodio = repositorioEpisodio.getEpisodioById(episodioId);

		TextView episodioTitle = (TextView) findViewById(R.id.episodioTitle);
		episodioTitle.setText(episodio.getTitle());

		TextView episodioPubDate = (TextView) findViewById(R.id.episodioPubDate);
		episodioPubDate.setText("Publicado: " + episodio.getPubDateFormated());

		TextView episodioDuration = (TextView) findViewById(R.id.episodioDuration);
		episodioDuration.setText("Duração: " + episodio.getDurationFormated());

		TextView episodioDescription = (TextView) findViewById(R.id.episodioDescription);
		episodioDescription.setText(episodio.getDescription());

		TextView episodioPostUrl = (TextView) findViewById(R.id.episodioPostUrl);
		episodioPostUrl.setText(Html.fromHtml("<a href='"+ episodio.getPostUrl() + "'>Veja mais aqui :)</a> "));
		episodioPostUrl.setMovementMethod(LinkMovementMethod.getInstance());

		Button episodioDownload = (Button) findViewById(R.id.episodioDownload);
		episodioDownload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				downloadFile();
			}
		});
		
		if(!episodio.getPath().equals("")){
			episodioDownload.setVisibility(View.GONE);
		}
		
		startTimeField = (TextView) findViewById(R.id.textView1);
		endTimeField = (TextView) findViewById(R.id.textView2);
		seekbar = (SeekBar) findViewById(R.id.seekBar1);
		playButton = (ImageButton) findViewById(R.id.imageButton1);
		pauseButton = (ImageButton) findViewById(R.id.imageButton2);				

	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public void play(View view) {
		
		dialog = ProgressDialog.show(this, "Exemplo legal", "Iniciando episódio...");					
		
		if(mediaPlayer == null){
			if(!episodio.getPath().equals("")){
				//episódio armazenado no dispositivo
				try {
					mediaPlayer = new MediaPlayer();
					FileInputStream fileStream = new FileInputStream(episodio.getPath());					
					mediaPlayer.setDataSource(fileStream.getFD());
					mediaPlayer.prepare();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				//episódio via streamingorração
				Uri episodioUri = Uri.parse(episodio.getFileUrl());
			
				mediaPlayer = MediaPlayer.create(this, episodioUri);
			}
		}
			
			
		seekbar.setClickable(false);
		pauseButton.setEnabled(false);
		
		Toast.makeText(getApplicationContext(), "Playing sound",Toast.LENGTH_SHORT).show();
		mediaPlayer.start();
		finalTime = mediaPlayer.getDuration(); //Double.parseDouble(episodio.getDuration());
		startTime = mediaPlayer.getCurrentPosition();
		if (oneTimeOnly == 0) {
			seekbar.setMax((int) finalTime);
			oneTimeOnly = 1;
		}

		endTimeField.setText(String.format(
				"%02d:%02d:%02d",
				TimeUnit.MILLISECONDS.toHours((long) finalTime),
				TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
				TimeUnit.MILLISECONDS.toSeconds((long) finalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))));
		startTimeField.setText(String.format(
				"%02d:%02d:%02d",
				TimeUnit.MILLISECONDS.toHours((long) startTime),
				TimeUnit.MILLISECONDS.toMinutes((long) startTime),
				TimeUnit.MILLISECONDS.toSeconds((long) startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
		seekbar.setProgress((int) startTime);
		myHandler.postDelayed(UpdateSongTime, 100);
		pauseButton.setEnabled(true);
		playButton.setEnabled(false);
		dialog.dismiss();
	}

	private Runnable UpdateSongTime = new Runnable() {
		@TargetApi(Build.VERSION_CODES.GINGERBREAD)
		public void run() {
			dialog.dismiss();
			startTime = mediaPlayer.getCurrentPosition();
			startTimeField.setText(String.format(
					"%02d:%02d:%02d",
					TimeUnit.MILLISECONDS.toHours((long) startTime),
					TimeUnit.MILLISECONDS.toMinutes((long) startTime),
					TimeUnit.MILLISECONDS.toSeconds((long) startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
			seekbar.setProgress((int) startTime);
			myHandler.postDelayed(this, 100);
		}
	};

	public void pause(View view) {
		Toast.makeText(getApplicationContext(), "Pausing sound", Toast.LENGTH_SHORT).show();

		mediaPlayer.pause();
		pauseButton.setEnabled(false);
		playButton.setEnabled(true);
	}

	public void forward(View view) {
		int temp = (int) startTime;
		if ((temp + forwardTime) <= finalTime) {
			startTime = startTime + forwardTime;
			mediaPlayer.seekTo((int) startTime);
		} else {
			Toast.makeText(getApplicationContext(),"Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
		}

	}

	public void rewind(View view) {
		int temp = (int) startTime;
		if ((temp - backwardTime) > 0) {
			startTime = startTime - backwardTime;
			mediaPlayer.seekTo((int) startTime);
		} else {
			Toast.makeText(getApplicationContext(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
		}

	}
	
	public void downloadFile() {
		
		pDialog = new ProgressDialog(this);
        pDialog.setMessage("Baixando arquivo. Aguarde...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(99);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(true);
        pDialog.show();		                		
		
        new DownloadFileFromURL().execute();

    }
	
	
	class DownloadFileFromURL extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {

			try {	        		        	
	        	String urlepisodio = episodio.getFileUrl();
	        	         
	            URL u = new URL(urlepisodio);
	            HttpURLConnection c = (HttpURLConnection) u.openConnection();
	            c.setRequestMethod("GET");
	            c.setDoOutput(true);
	            c.connect();
	   
	            FileOutputStream f = new FileOutputStream(new File("/mnt/sdcard/Podcasts",FilenameUtils.getBaseName(urlepisodio)+"."+FilenameUtils.getExtension(urlepisodio)));
	            InputStream in = c.getInputStream();
	
	            byte[] buffer = new byte[1024];
	            int len1 = 0;
	            double percTot = c.getContentLength();
	            double percAtual = 0;
	            double count = 1;
	            while ((len1 = in.read(buffer)) > 0) {
	            	percAtual = percAtual + len1;
	            	if((percAtual/percTot)*100 > count)
	            	{
	            		Log.e("Porcentagem", " "+ (int)((percAtual/percTot)*100) + "%");
	            		pDialog.setProgress((int)((percAtual/percTot)*100));
	            		count = count + 1;
	            	}
	              f.write(buffer, 0, len1);
	            }
	            f.close();
	        } catch (Exception e) {	        	
	        	e.printStackTrace();
	        }finally {	        	
	        	Log.e("file.getAbsolutePath()","/mnt/sdcard/Podcasts"+"/"+FilenameUtils.getBaseName(episodio.getFileUrl())+"."+FilenameUtils.getExtension(episodio.getFileUrl()));	        	
	        	
	        	episodio.setPath("/mnt/sdcard/Podcasts"+"/"+FilenameUtils.getBaseName(episodio.getFileUrl())+"."+FilenameUtils.getExtension(episodio.getFileUrl()));
	        	repositorioEpisodio.update(episodio);	        	
	        	
	        	pDialog.dismiss();
	        	
	        	finish();
	        	startActivity(getIntent());
	        	
	        }
			
			return null;
		}
		
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu; this adds items to the action bar if it is present.
	    getMenuInflater().inflate(R.menu.menu_episodio, menu);
	    MenuItem item = menu.findItem(R.id.menu_item_share);
	    mShareActionProvider = (ShareActionProvider) item.getActionProvider();
	    // Create the share Intent	    
	    String yourShareText = episodio.getTitle()+" - "+episodio.getPostUrl();
	    Intent shareIntent = ShareCompat.IntentBuilder.from(this).setType("text/plain").setText(yourShareText).getIntent();
	    // Set the share Intent
	    mShareActionProvider.setShareIntent(shareIntent);
	    return true;
	}

}
