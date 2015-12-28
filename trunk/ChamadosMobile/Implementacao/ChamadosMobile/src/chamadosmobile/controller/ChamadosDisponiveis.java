package chamadosmobile.controller;

import java.util.ArrayList;
import java.util.List;

import pojo.Chamado;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ChamadosDisponiveis extends ListActivity {

	private List<Chamado> chamados = new ArrayList<>();

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// TODO: Popular Lista de chamados com o serviço

		Chamado c = new Chamado();
		c.setDescricao("MOUSE QUEBRADO");
		Chamado c2 = new Chamado();
		c2.setDescricao("IMPRESSORA SEM TINTA");
		chamados.add(c);
		chamados.add(c2);

		this.setListAdapter(new ChamadosAdapter(this, chamados));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);
		// Mostrar na tela mensagem com o nome do ítem selecionado
		Object o = this.getListAdapter().getItem(position);
		Toast.makeText(this, "Você clicou : " + o.toString(), Toast.LENGTH_LONG)
				.show();
		startActivity(new Intent(this, ChamadosDisponiveis.class));
	}

}
