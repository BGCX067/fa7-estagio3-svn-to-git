package pedidoonline.service

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = "metaClass,class,categoria")
class Item {
	
	Long codigo
	String nome
	String descricao
	BigDecimal valor
	String imagem
	
    static constraints = {
    }
	
	String toString() {
		return nome
	}
}
