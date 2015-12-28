package controller;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private String[] itensMenu;
	private static String itens = "";

	@Override
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);

		// Criar um array de Strings, que serÃ¡ utilizado em seu ListActivity

		itensMenu = new String[] { "Chamados Disponíveis",
				"Consultar Equipamento", "Iniciar Atendimento",
		"Finalizar Atendimento" };

		// Criar um ArrayAdapter, que vai fazer aparecer as Strings acima em seu
		// ListView

		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, itensMenu));

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);

		// Mostrar na tela mensagem com o nome do Ã­tem selecionado
		// Toast.makeText(this, "VocÃª clicou : " + o.toString(),
		// Toast.LENGTH_LONG)
		// .show();
		Object o = this.getListAdapter().getItem(position);
		itens = o.toString();
		startActivity(new Intent(this, retornaClasse()));

	}

	public Class retornaClasse() {
		switch (itens) {
		case "Chamados Disponíveis":
			return ChamadosDisponiveis.class;
		case "Consultar Equipamento":
			return ConsultarEquipamento.class;
		case "Iniciar Atendimento":
			return MeusChamados.class;
		case "Finalizar Atendimento":
			return FinalizarAtendimento.class;
		default:
			return null;
		}
	}

}
