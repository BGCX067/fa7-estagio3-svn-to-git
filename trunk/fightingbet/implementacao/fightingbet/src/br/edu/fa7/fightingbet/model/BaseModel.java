package br.edu.fa7.fightingbet.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.annotations.SerializedName;

public class BaseModel {

	@SerializedName("id")
	private Long id;

	@SerializedName("created_at")
	private Date dataCriacao;
	
	@SerializedName("updated_at")
	private Date dataAtualizacao;
	
	@SerializedName("msg")
	private String message;
	
	@SerializedName("error")
	private String error;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public boolean isMessage() {
		return StringUtils.isNotBlank(message);
	}

	public boolean isSuccess() {
		return isMessage() && message.equalsIgnoreCase("success");
	}
	
	public boolean isError() {
		return StringUtils.isNotBlank(error);
	}
}
