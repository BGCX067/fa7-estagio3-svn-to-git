package pojo;

import java.util.Date;


public class Chamado {
	private int id;
	private String descricao;
	private String requisitante;
	private String status;
	private Date dataFechamento;
	private Date atualizadoEm;
	private Date dataUltimoPlay;
	private String sla;
	private String textoRetorno;
	private ChamadoTipoProblema chamadoTipoProblema;
	private Setor setorRequisitante;
	public Chamado() {
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

	public String getRequisitante() {
		return requisitante;
	}

	public void setRequisitante(String requisitante) {
		this.requisitante = requisitante;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSla() {
		return sla;
	}

	public void setSla(String sla) {
		this.sla = sla;
	}

	public String getTextoRetorno() {
		return textoRetorno;
	}

	public void setTextoRetorno(String textoRetorno) {
		this.textoRetorno = textoRetorno;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Date getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(Date atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	public Date getDataUltimoPlay() {
		return dataUltimoPlay;
	}

	public void setDataUltimoPlay(Date dataUltimoPlay) {
		this.dataUltimoPlay = dataUltimoPlay;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ChamadoTipoProblema getChamadoTipoProblema() {
		return chamadoTipoProblema;
	}

	public void setChamadoTipoProblema(ChamadoTipoProblema chamadoTipoProblema) {
		this.chamadoTipoProblema = chamadoTipoProblema;
	}

	public Setor getSetorRequisitante() {
		return setorRequisitante;
	}

	public void setSetorRequisitante(Setor setorRequisitante) {
		this.setorRequisitante = setorRequisitante;
	}

	@Override
	public String toString() {
		return "Chamado [id=" + id + ", descricao=" + descricao
				+ ", requisitante=" + requisitante + ", status=" + status
				+ ", dataFechamento=" + dataFechamento + ", atualizadoEm="
				+ atualizadoEm + ", dataUltimoPlay=" + dataUltimoPlay
				+ ", sla=" + sla + ", textoRetorno=" + textoRetorno
				+ ", chamadoTipoProblema=" + chamadoTipoProblema
				+ ", setorRequisitante=" + setorRequisitante + "]";
	}

}
