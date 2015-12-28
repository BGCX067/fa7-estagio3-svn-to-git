package chamadosmobile.controller;
import java.util.List;

import pojo.Chamado;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import chamadosmobile.controller.R;

public class ChamadosAdapter extends BaseAdapter {

	private Context context;
	private List<Chamado> lista;

	public ChamadosAdapter(Context c, List<Chamado> chamados) {
		this.context = c;
		lista = chamados;
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Recupera o Chamado da posicao atual
		Chamado c = lista.get(position);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.chamados_disponiveis, null);

		// Atualiza o valor do TextView
		TextView chamado = (TextView) view.findViewById(R.id.chamados);
		String descricaoChamado = c.getDescricao();
		chamado.setText(descricaoChamado);

		return view;
	}

}
