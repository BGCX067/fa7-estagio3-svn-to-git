package pedidoonline.service

class Fatura {

	BigDecimal valorTotal
	BigDecimal valorParcial
	
	static belongsTo = [conta : Conta]
	
    static constraints = {
    }
}
