package pedidoonline.service

class Mesa {

	Long numero
	
	static belongsTo = [area: Area]
	
    static constraints = {
    }
}
