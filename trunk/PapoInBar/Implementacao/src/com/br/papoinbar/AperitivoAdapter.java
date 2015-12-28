package com.br.papoinbar;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AperitivoAdapter extends CursorAdapter {

	// Context context;
	// Cursor cursor;

	static class ViewHolder {
		int itemQntde=1;
		ImageButton add;
		ImageButton minus;
		TextView nome;
		TextView preco;
		TextView qnt;
	}

//	private LayoutInflater cursorInflater;
//	ImageButton imgBtnAdd;
//	ImageButton imgBtnMinus;
//	int qntde = 1;
//	TextView txtNome;
//	TextView txtPreco;
//	TextView txtQntde;

	public AperitivoAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
//		cursorInflater = (LayoutInflater) context
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// this.context = context;
		// this.cursor = c;
	}

	@Override
	public void bindView(View view, Context context, final Cursor cursor) {
		// TODO Auto-generated method stub
		// qntde = cursor.getInt(4);
		final ViewHolder holder = (ViewHolder) view.getTag();
		holder.nome = (TextView) view.findViewById(R.id.nomeAperitivo);
		holder.preco = (TextView) view.findViewById(R.id.valorAperitivo);
		holder.qnt = (TextView) view.findViewById(R.id.txtQuantidade);
		holder.nome.setText(cursor.getString(1));
		holder.preco.setText(cursor.getString(5));
		holder.qnt.setText(String.valueOf(holder.itemQntde));
		holder.add = (ImageButton) view.findViewById(R.id.add);
		holder.minus = (ImageButton) view.findViewById(R.id.remove);
		holder.add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				holder.itemQntde++;

				holder.preco.setText(String.format("R$%8.2f X %d = R$%8.2f",
						cursor.getDouble(2), holder.itemQntde, cursor.getDouble(2) * holder.itemQntde));
				holder.qnt.setText(String.valueOf(holder.itemQntde));
				Log.v("MyActivity", "Mais");
			}
		});

		holder.minus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (holder.itemQntde > 1) {
					holder.itemQntde--;
				}
				holder.qnt.setText(String.valueOf(holder.itemQntde));
				holder.preco.setText(String.format("R$%8.2f X %d = R$%8.2f",
						cursor.getDouble(2), holder.itemQntde, cursor.getDouble(2) * holder.itemQntde));
				Log.v("MyActivity", "Menos");
			}
		});
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View cursorInflater = ((LayoutInflater) context
                .getSystemService("layout_inflater")).inflate(
                R.layout.list_view_aperitivo, parent, false);
		 ViewHolder holder = new ViewHolder();
	        holder.add = (ImageButton)cursorInflater.findViewById(R.id.add);
	        holder.minus = (ImageButton)cursorInflater.findViewById(R.id.remove);
	        holder.nome = (TextView)cursorInflater.findViewById(R.id.nomeAperitivo);
	        holder.preco = (TextView)cursorInflater.findViewById(R.id.valorAperitivo);
	        holder.qnt = (TextView)cursorInflater.findViewById(R.id.txtQuantidade);
	        cursorInflater.setTag(holder);
	        return cursorInflater;
//		return cursorInflater.inflate(R.layout.list_view_aperitivo, parent,
//				false);
	}
}
