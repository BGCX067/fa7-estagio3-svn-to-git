package br.edu.fa7.fightingbet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Noticia extends BaseModel implements Parcelable {
	
	@SerializedName("titulo")
	private String titulo;
	
	@SerializedName("texto")
	private String texto;
	
	public Noticia() {
		
	}
	
    public Noticia(Long id, String titulo, String texto) { 
    	this.setId(id);
        this.setTitulo(titulo);
        this.setTexto(texto);
    }
	
	public Noticia(Parcel source) {
        this.setId(source.readLong());
        this.setTitulo(source.readString());
        this.setTexto(source.readString());
    }

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	@Override
	public String toString() {
	    return this.titulo; 
	}

	@Override
	public int describeContents() {
		return this.hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(getId());
		dest.writeString(getTitulo());
		dest.writeString(getTexto());
	}
	
	public static final Parcelable.Creator<Noticia> CREATOR = new Parcelable.Creator<Noticia>() {
		public Noticia createFromParcel(Parcel in) {
		    return new Noticia(in);
		}
		
		public Noticia[] newArray(int size) {
		    return new Noticia[size];
		}
	};
}