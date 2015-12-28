package pedidoonline.service

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = "metaClass,class")
class Cardapio {

	String nomeEstabelecimento
	
	
	static hasMany = [categorias: Categoria]
	
    static constraints = {
		
    }
}
