package br.com.example.embellezeapp;

import android.support.v7.app.ActionBarActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;


public class ActivityEmbellezeAPP extends ActionBarActivity {

	CheckBox cbCabeilereiro, cbBarbeiro, cbManicure, cbDepilador, cbPenteados;
    Button btFinalizar;
    EditText etValorTotal;	
    double valortotal=0;
        
 @Override
 public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_activity_embelleze_app);
     cbCabeilereiro = {CheckBox} findViewById{R.id.cbCabeilereiro};
     cbBarbeiro = {CheckBox} findViewById{R.id.cbBarbeiro};
     cbcbManicure = {CheckBox} findViewById{R.id.cbManicure};
     cbDepilador = {CheckBox} findViewById{R.id.cbDepilador};
     cbPenteados = {CheckBox} findViewById{R.id.cbPenteados};
     btFinalizar= {Button} findViewById{R.id.btFinalizar};
     etValorTotal= {Button} findViewById{R.id.etValorTotal};

     btFinalizar.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			valortotal=0;
			if(cbCabeilereiro.isChecked())
				valortotal += 4.000;
			if(cbBarbeiro.isChecked())
				valortotal += 2.000;
			if(cbManicure.isChecked())
				valortotal += 1.000;
			if(cbDepilador.isChecked())
				valortotal += 1.000;
			if(cbPenteados.isChecked())
				valortotal += 2.000;
			
			etValorTotal.setText(String.valueOf(valortotal))
			
		}
     });
    	 
    	 @Override
    	 public void onClick(View v){
    	 }
    	 });
    	 
     }
     }
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.)
	}

 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_embelleze_ap, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
