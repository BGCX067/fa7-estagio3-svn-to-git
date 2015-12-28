package chamadosmobile.controller;

import java.util.ArrayList;
import java.util.List;

import pojo.Chamado;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ChamadosDisponiveis extends ListActivity {


	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		RestAdapter restAdapter = new RestAdapter.Builder()
		.setEndpoint("http://portal.blcm.cialne.com.br:8787/chamados_mobile")
		.build();
		ApiService service = restAdapter.create(ApiService.class);
		service.buscarTodosChamadosDisponiveis(new Callback<List<Chamado>>() {

			@Override
			public void success(List<Chamado> arg0, Response arg1) {
				System.out.println(arg0.toString());
				setListAdapter(new ChamadosAdapter(ChamadosDisponiveis.this, new ArrayList<Chamado>()));
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
		// Mostrar na tela mensagem com o nome do ítem selecionado
		Chamado o = (Chamado) this.getListAdapter().getItem(position);
		Toast.makeText(this, "Você clicou : " + o.getDescricao(),
				Toast.LENGTH_LONG).show();
		startActivity(new Intent(this, ChamadosDisponiveis.class));
	}

}
