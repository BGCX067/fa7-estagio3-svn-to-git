package br.com.example.vendasapp;




import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

	CheckBox cbCabeleireiro_Profissional, cbBarbeiro_Profissional,
	cbDepilacao_Profissional, cbManicure_Pedicure, cbMaquiador_Profissional;
	
	Button btFinalizar ;
	EditText etValorTotal;
	
	double valorCabeleireiro_Profissional=0;
    double valorBarbeiro_Profissional=0;
    double valorDepilacao_Profissional=0;
    double valorManicure_Pedicure=0;
    double valorMaquiador_Profissional=0;
    double valortotal=0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        chamaMenuPrincipal(); // vai chamar o menu principal
        
        
        
        
          
        
       

            
        
    }
    
    public void chamaCadastro(){
    	setContentView(R.layout.cadastro);
    	
    	Button btMenuPrincipal = (Button) findViewById(R.id.btMenuPrincipal);
    	btMenuPrincipal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamaMenuPrincipal();
				
			}
		});
    	
    }
    
    public void chamaConsulta(){
    	setContentView(R.layout.consulta);
    	
    	Button btVoltar = (Button) findViewById(R.id.btVoltar);
    	
    	btVoltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamaMenuPrincipal();
				
			}
		});
    	
    }
    public void chamaCompras(){
    	setContentView(R.layout.compras);
    	
    	Button btVoltarMenu = (Button) findViewById(R.id.btVoltarMenu);
    	
    	//começo
    	
    	cbCabeleireiro_Profissional = (CheckBox) findViewById(R.id.cbCabeleireiro_Profissional);
        cbBarbeiro_Profissional = (CheckBox) findViewById(R.id.cbBarbeiro_Profissional);
        cbDepilacao_Profissional = (CheckBox) findViewById(R.id.cbDepilacao_Profissional);
        cbManicure_Pedicure = (CheckBox) findViewById(R.id.cbManicure_Pedicure);
        cbMaquiador_Profissional = (CheckBox) findViewById(R.id.cbMaquiador_Profissional);
        btFinalizar = (Button) findViewById(R.id.btFinalizar);
        etValorTotal = (EditText) findViewById(R.id.etValorTotal);
        
        btFinalizar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				valortotal=0;
				if(cbCabeleireiro_Profissional.isChecked())
					valortotal+= 4000;
				if(cbBarbeiro_Profissional.isChecked())
					valortotal+= 3000;
				if(cbDepilacao_Profissional.isChecked())
					valortotal+= 2000;
				if(cbManicure_Pedicure.isChecked())
					valortotal+= 2000;
				if(cbMaquiador_Profissional.isChecked())
					valortotal+= 2000;
				
				
				etValorTotal.setText(String.valueOf(valortotal));
				if (valortotal > 5000)
				{
					AlertDialog.Builder caixaAlerta = 
							new AlertDialog.Builder(MainActivity.this);
					caixaAlerta.setMessage("Obrigado pela compra! você terá 10% de desconto.");
					caixaAlerta.setTitle("Alerta de compra");
					caixaAlerta.setNeutralButton("OK", null);
					caixaAlerta.show();
				}
			}
		});
    	
    	//fim
    	    	   	
    	
    	btVoltarMenu.setOnClickListener(new View.OnClickListener() {
		
    		@Override
			public void onClick(View v) {
				chamaMenuPrincipal();
				
			}
		});
    }
    public void chamaMenuPrincipal(){
    	setContentView(R.layout.activity_main);
    	
    	
    	 Button btCadastro = (Button) findViewById(R.id.btCadastro);
         Button btConsulta = (Button) findViewById(R.id.btConsulta);
         Button btCompras = (Button) findViewById(R.id.btCompras);
         
         
         
         
         
         	
         btCadastro.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				chamaCadastro();
 			}
 		});
         
         btConsulta.setOnClickListener(new View.OnClickListener(){
         	
         	public void onClick(View v){
         		chamaConsulta();
            	} 
         });
         
         btCompras.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				chamaCompras();
 			}
 		});
         
    	      
    }
    
    
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
