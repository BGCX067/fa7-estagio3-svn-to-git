package com.example.suasviagens;

import com.example.suasviagens.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

@SuppressWarnings("unused")
public class DashboardActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

	}

	public void selecionarOpcao(View view){

		TextView textView = (TextView) view;

		String opcao = "Opção: " + textView.getText().toString();

		Toast.makeText(this, opcao, Toast.LENGTH_SHORT).show();

		switch (view.getId()) {
		case R.id.nova_viagem:
			startActivity(new Intent(this,ViagemActivity.class));
			break;
		//case R.id.novo_gasto:
			//startActivity(new Intent(this,GastoActivity.class));
			//break;
		case R.id.minhas_viagens:
			startActivity(new Intent(this,ViagemListActivity.class));
			break;
		case R.id.configuracoes:
			startActivity(new Intent(this,ConfiguracoesActivity.class));
			break;
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.dashboard_menu, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item){
		finish();
		return true;
	}
	
}