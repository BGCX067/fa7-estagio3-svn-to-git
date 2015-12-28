package pedidoonline.service

class Conta {

	Long codigo
	Mesa mesa
	
	static belongsTo = [fatura : Fatura]
	static hasMany = [pedidos: Pedido]
	
    static constraints = {
    }
}
