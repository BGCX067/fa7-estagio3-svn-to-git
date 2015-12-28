package pedidoonline.service

import java.util.Set;

class Categoria {

	String nome
	byte[] imagem
	Set<Item> itens
	
	static hasMany = [itens: Item]
	
    static constraints = {
		nome(blank:false, size:3..20)
		imagem (nullable:true, maxSize:1073411824)
    }
	
	String toString(){
		return nome
	}
}
