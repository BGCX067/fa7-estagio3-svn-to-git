package br.com.artssabores.activity.testehttp;

import java.net.HttpURLConnection;

import br.com.artssabores.R;
import br.com.artssabores.http.Http;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BuscarArquivoTexto extends Fragment implements OnClickListener,
		Runnable {

	protected static final String CATEGORIA = "livro";
	protected static final String URL = "http://192.168.43.119:8080/servidor-teste/arquivo.txt";
	// handler utilizada para atualizar a view
	private Handler handler = new Handler();
	private ProgressDialog dialog;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.arquivo, container);
		Button b = (Button) view.findViewById(R.id.btDownload);

		return view;
	}

	@Override
	public void onClick(View v) {
		/*dialog = ProgressDialog.show(this, "Exemplo",
				"Buscando texto, por favor aguarde...", false, true);
		// faz o download em uma thread*/
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			// Faz o Download
			final String arquivo = Http.getIntance(Http.NORMAL)
					.downloadArquivo(URL);

			Log.i(CATEGORIA, "Texto retornado: " + arquivo);

			// Precisa do Handler para atualizar a view de outra thread
			handler.post(new Runnable() {

				@Override
				public void run() {
					TextView text = (TextView) view.findViewById(R.id.texto);
					text.setText(arquivo);
				}
			});

		} catch (Throwable e) {
			Log.i(CATEGORIA, e.getMessage(), e);
		} finally {
			dialog.dismiss();
		}

	}
}
