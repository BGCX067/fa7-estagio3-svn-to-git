package pojo;

import java.io.Serializable;

public class Equipamento implements Serializable{

	private static final long serialVersionUID = 7803436738567074154L;

	private int id;
	private String estadoEquipamento;
	private String serialNumber;
	private boolean telefonia;
	private ModeloEquipamento modelo;
	private Responsavel responsavel;

	public Equipamento() {

	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstadoEquipamento() {
		return estadoEquipamento;
	}

	public void setEstadoEquipamento(String estadoEquipamento) {
		this.estadoEquipamento = estadoEquipamento;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public boolean isTelefonia() {
		return telefonia;
	}

	public void setTelefonia(boolean telefonia) {
		this.telefonia = telefonia;
	}

	public ModeloEquipamento getModelo() {
		return modelo;
	}

	public void setModelo(ModeloEquipamento modelo) {
		this.modelo = modelo;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
}
