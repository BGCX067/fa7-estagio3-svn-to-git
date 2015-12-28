package pedidoonline.service

class Area {
	
	Long codigo
	
	static hasMany = [mesas: Mesa, garcons: Garcom]
	
    static constraints = {
    }
}
