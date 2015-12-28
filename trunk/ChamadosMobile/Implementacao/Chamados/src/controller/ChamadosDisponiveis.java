package controller;

import java.util.List;

import pojo.Chamado;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import service.ApiService;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.Toast;

public class ChamadosDisponiveis extends ListActivity {


	ApiService service;
	RestAdapter restAdapter;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		TableRow row = new TableRow(this);
		row.setClickable(true);

		restAdapter = new RestAdapter.Builder()
		.setEndpoint("http://portal.blcm.cialne.com.br:8787/chamados_mobile")
		.build();

		service = restAdapter.create(ApiService.class);
		service.buscarTodosChamadosDisponiveis(new Callback<List<Chamado>>() {

			@Override
			public void success(List<Chamado> arg0, Response arg1) {
				setListAdapter(new ChamadosAdapter(ChamadosDisponiveis.this, arg0));
			}

			@Override
			public void failure(RetrofitError arg0) {
				System.out.println(arg0.toString());
			}
		});

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Chamado o = (Chamado) this.getListAdapter().getItem(position);
		Toast.makeText(this, o.getDescricao(),
				Toast.LENGTH_LONG).show();
		startActivity(new Intent(this, ChamadosDisponiveis.class));
	}

}
