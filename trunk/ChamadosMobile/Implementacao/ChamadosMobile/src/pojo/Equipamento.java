package pojo;

public class Equipamento {

	private Integer id;
	private String descricao;
	private String responsavelEquipamento;
	private String setorResponsavel;
	private Integer serial;

	public Equipamento() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getResponsavelEquipamento() {
		return responsavelEquipamento;
	}

	public void setResponsavelEquipamento(String responsavelEquipamento) {
		this.responsavelEquipamento = responsavelEquipamento;
	}

	public String getSetorResponsavel() {
		return setorResponsavel;
	}

	public void setSetorResponsavel(String setorResponsavel) {
		this.setorResponsavel = setorResponsavel;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

}
