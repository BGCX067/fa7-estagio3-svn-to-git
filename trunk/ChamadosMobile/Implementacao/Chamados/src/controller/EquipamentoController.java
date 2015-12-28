package controller;

import pojo.Equipamento;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.appcompat.R;
import android.widget.TextView;

public class EquipamentoController extends Activity{

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.equipamento);
		Equipamento equipamento = (Equipamento) getIntent().getSerializableExtra("equipamento");
		TextView descricao = (TextView) findViewById(R.id.descricao);
		TextView serial = (TextView) findViewById(R.id.serial);
		TextView responsavel = (TextView) findViewById(R.id.responsavel);

		if (equipamento != null){
			descricao.setText(equipamento.getModelo().getDescricao());
			serial.setText(equipamento.getSerialNumber());
			responsavel.setText(equipamento.getResponsavel().getNome());
		}
	}

}
