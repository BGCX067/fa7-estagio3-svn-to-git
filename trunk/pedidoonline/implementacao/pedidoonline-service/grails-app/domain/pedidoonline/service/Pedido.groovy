package pedidoonline.service

class Pedido {

	Long codigo

	static hasMany = [itens: ItemPedido]

	static mapping = {
		codigo generator:'sequence', params:[name:'hibernate_sequence'] 
	}

	static constraints = {
	}
}
