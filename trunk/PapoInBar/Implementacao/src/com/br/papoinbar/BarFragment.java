package com.br.papoinbar;

import com.br.papoinbar.modelo.Bar;
import com.br.papoinbar.modelo.TipoDeBar;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class BarFragment extends Fragment {
	
	public BarFragment(){}
	ListView list;
	DBAdapter datasource;
	SimpleCursorAdapter adapter;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bar, container, false);

        Button cadastrarBar = (Button) rootView.findViewById(R.id.cadastrarBar);
        
        datasource = new DBAdapter(this.getActivity());
        datasource.open();
        Cursor cursor = datasource.getBares();
        
        //Bar
        
        String[] columns = new String[] { "nome"};
        int[] to = new int[] {android.R.id.text1};

        adapter = new SimpleCursorAdapter(this.getActivity(), android.R.layout.simple_list_item_2, cursor, columns, to,0);

        // Assign adapter to ListView
        list = (ListView)rootView.findViewById(R.id.listBares);
        
        list.setAdapter(adapter); 
        
        // ListView Item Click Listener
        list.setOnItemClickListener(new OnItemClickListener() {

              @Override
              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
            	Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            	startActivityForResult(new Intent(getActivity(), VisualizarBarActivity.class).putExtra("bar", cursor.getLong(0)),1);
            
              }
        });
        
        cadastrarBar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cadastrarBar();
				
			}
		});
         
        return rootView;

    }
	private void cadastrarBar(){
		startActivityForResult(new Intent(getActivity(),CadastroBarActivity.class), 1);;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		   datasource = new DBAdapter(this.getActivity());
	        datasource.open();
	        Cursor cursor = datasource.getBares();
	        
	        //Bar
	        
	        String[] columns = new String[] { "nome"};
	        int[] to = new int[] {android.R.id.text1,};

	        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this.getActivity(), android.R.layout.simple_list_item_2, cursor, columns, to,0);

	        // Assign adapter to ListView
	        list = (ListView)this.getActivity().findViewById(R.id.listBares);
	        
	        list.setAdapter(adapter); 
		super.onActivityResult(requestCode, resultCode, data);
	}

}
