package br.edu.fa7.fightingbet.model;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Evento extends BaseModel implements Parcelable {
	
	@SerializedName("nome")
	private String nome;
	
	@SerializedName("data_hora")
	private Date dataHora;
	
	@SerializedName("url")
	private String url;
	
	@SerializedName("local")
	private String local;
	
	private String dataHoraStr;
	
	public Evento() {
		
	}
	
    public Evento(Long id, String nome, String local, String dataHoraStr) { 
    	this.setId(id);
        this.setNome(nome);
        this.setLocal(local);
        this.setDataHoraStr(local);
    }
	
	public Evento(Parcel source) {
        this.setId(source.readLong());
        this.setNome(source.readString());
        this.setLocal(source.readString());
        this.setDataHoraStr(source.readString());
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	public String getDataHoraStr() {
		return dataHoraStr;
	}

	public void setDataHoraStr(String dataHoraStr) {
		this.dataHoraStr = dataHoraStr;
	}
	
	@Override
	public String toString() {
	    return this.nome; 
	}
	
	@Override
	public int describeContents() {
		return this.hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(getId());
		dest.writeString(getNome());
		dest.writeString(getLocal());
		dest.writeString(DateFormatUtils.format(getDataHora(), "dd/MM/yyyy HH:mm"));
	}
	
	public static final Parcelable.Creator<Evento> CREATOR = new Parcelable.Creator<Evento>() {
		public Evento createFromParcel(Parcel in) {
		    return new Evento(in);
		}
		
		public Evento[] newArray(int size) {
		    return new Evento[size];
		}
	};
}
