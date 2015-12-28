package br.com.artssabores.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.artssabores.model.Produto;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CosumirJsonActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new DownloadJsonAsyncTask()
				.execute("http://192.168.43.119:8080/apirest/services/produtos/listargson");
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}

	class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Produto>> {
		ProgressDialog dialog;

		// Exibe pop-up indicando que está sendo feito o download do JSON
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(CosumirJsonActivity.this, "Aguarde",
					"Fazendo download JSON");
		}

		// Acessa o serviço do JSON e retorna a lista de pessoas
		@Override
		protected List<Produto> doInBackground(String... params) {
			String urlString = params[0];
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(urlString);
			try {
				HttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					String json = getStringFromInputStream(instream);
					instream.close();
					List<Produto> produtos = getProdutos(json);
					return produtos;
				}
			} catch (Exception e) {
				Log.e("Erro", "Falha ao acessar Web service", e);
			}
			return null;
		}

		// Depois de executada a chamada do serviço
		@Override
		protected void onPostExecute(List<Produto> result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (result.size() > 0) {
				ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(
						CosumirJsonActivity.this,
						android.R.layout.simple_list_item_1, result);
				setListAdapter(adapter);
			} else {
				AlertDialog.Builder buider = new AlertDialog.Builder(
						CosumirJsonActivity.this)
						.setTitle("ERRO")
						.setMessage("Não foi possivel acessar as informações!!")
						.setPositiveButton("OK", null);
				buider.create().show();
			}
		}

		// Retorna uma lista de pessoas com as informações retornadas do JSON
		private List<Produto> getProdutos(String json) {
			List<Produto> produtos = new ArrayList<Produto>();

			try {
				JSONArray produtosJson = new JSONArray(json);
				JSONObject produto;

				for (int i = 0; i < produtosJson.length(); i++) {
					produto = new JSONObject(produtosJson.getString(i));
					Log.i("Pessoa encontrada: ",
							"nome " + produto.getString("nome"));
					Produto objetoProduto = new Produto();
					objetoProduto.setNome(produto.getString("nome"));
					objetoProduto.setDescricao(produto.getString("descricao"));
					produtos.add(objetoProduto);
				}

			} catch (JSONException e) {
				Log.e("ERRO: ", "Erro no parse do JSON", e);
			}

			return produtos;
		}

		// Converte objeto InputStream para String
		private String getStringFromInputStream(InputStream is) {
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();

			String line;
			try {

				br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			return sb.toString();
		}

	}
}
