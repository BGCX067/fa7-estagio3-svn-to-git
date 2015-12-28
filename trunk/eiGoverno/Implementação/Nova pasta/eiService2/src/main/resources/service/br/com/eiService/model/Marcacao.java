package br.com.eiService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity @XmlRootElement
public class Marcacao {
       
        @Id @GeneratedValue
        public long id;
       
        public double latitude;
       
        public double longitude;
       
        public int tipoMarcacao;
       
        public String descricao;
       
        @ManyToOne
        @JoinColumn(name="autor_id")
        public Usuario usuario;

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public double getLatitude() {
                return latitude;
        }

        public void setLatitude(double latitude) {
                this.latitude = latitude;
        }

        public double getLongitude() {
                return longitude;
        }

        public void setLongitude(double longitude) {
                this.longitude = longitude;
        }

        public int getTipoMarcacao() {
                return tipoMarcacao;
        }

        public void setTipoMarcacao(int tipoMarcacao) {
                this.tipoMarcacao = tipoMarcacao;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public Usuario getUsuario() {
                return usuario;
        }

        public void setUsuario(Usuario usuario) {
                this.usuario = usuario;
        }
}
        