package br.com.pedidoonline.app;

import java.text.NumberFormat;
import java.util.Locale;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import br.com.pedidoonline.app.model.Cardapio;
import br.com.pedidoonline.app.model.Categoria;
import br.com.pedidoonline.app.model.Item;
import br.com.pedidoonline.app.service.PedidoFacade;

/**
 * This is adapter for expandable list-view for constructing the group and child
 * elements.
 */
public class ExpAdapter extends BaseExpandableListAdapter {

	private Context myContext;
	private PedidoFacade facade;
	private NumberFormat formato;

	public ExpAdapter(Context context, Cardapio cardapio) {
		this.myContext = context;
		facade = PedidoFacade.getInstance();
		formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		Item item = facade.getItem(groupPosition, childPosition);
		if(item != null) {
			return item.getNome();
		}
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		Item item = facade.getItem(groupPosition, childPosition);
		if(item != null) {
			item.getCodigo();
		}
		return 0L;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final Item item = facade.getItem(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.item_row, null);
		}

		// TextView tvPlayerName = new TextView(myContext);

		Button bAdd = (Button) convertView.findViewById(R.id.b_add);
		Button bSub = (Button) convertView.findViewById(R.id.b_sub);
		final EditText quantidade = (EditText) convertView.findViewById(R.id.quantidade);

		bAdd.setOnClickListener(new View.OnClickListener() {
			EditText myEdit = quantidade;
			Integer myQtd = Integer.parseInt(quantidade.getText().toString());

			@Override
			public void onClick(View v) {
				myQtd = Integer.parseInt(quantidade.getText().toString());
				myQtd++;
				myEdit.setText("" + myQtd);
				facade.pedir(item, myQtd);
			}
		});

		bSub.setOnClickListener(new View.OnClickListener() {
			EditText myEdit = quantidade;
			Integer myQtd = Integer.parseInt(quantidade.getText().toString());

			@Override
			public void onClick(View v) {
				myQtd = Integer.parseInt(quantidade.getText().toString());
				myQtd--;
				if (myQtd<0){
					myQtd = 0;
				}
				myEdit.setText("" + myQtd);
				facade.pedir(item, myQtd);
			}
		});

		TextView tvPlayerName = (TextView) convertView.findViewById(R.id.item_descricao);
		TextView valorUnitario = (TextView) convertView.findViewById(R.id.preco_item);
		if( item != null) {
			tvPlayerName.setText(item.getNome());
			quantidade.setText(facade.obterQuantidadePorItem(item).toString());
			String preco = "Preço: "+formato.format(item.getValor());
			valorUnitario.setText(preco);
		}

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return facade.getCategoria(groupPosition).getItens().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		Categoria categoria = facade.getCategoria(groupPosition);
		if(categoria != null) {
			return categoria.getNome();
		}
		return null;
	}

	@Override
	public int getGroupCount() {
		return facade.getCategorias().size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return facade.getCategoria(groupPosition).getId();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		TextView tvGroupName = new TextView(myContext);
		tvGroupName.setText(facade.getCategoria(groupPosition).getNome());
		tvGroupName.setPadding(60, 5, 0, 5);
		tvGroupName.setTextSize(20);
		tvGroupName.setTypeface(null, Typeface.BOLD);
		tvGroupName.setTextColor(myContext.getResources().getColor(R.color.black));
		tvGroupName.setBackgroundColor(myContext.getResources().getColor(R.color.silver));
		return tvGroupName;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}