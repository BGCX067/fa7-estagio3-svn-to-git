package pedidoonline.service

import grails.converters.JSON
import groovy.json.JsonSlurper

import org.springframework.dao.DataIntegrityViolationException

import com.google.gson.Gson;

class PedidoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pedidoInstanceList: Pedido.list(params), pedidoInstanceTotal: Pedido.count()]
    }

    def create() {
        [pedidoInstance: new Pedido(params)]
    }

    def save() {
        def pedidoInstance = new Pedido(params)
        if (!pedidoInstance.save(flush: true)) {
            render(view: "create", model: [pedidoInstance: pedidoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'pedido.label', default: 'Pedido'), pedidoInstance.id])
        redirect(action: "show", id: pedidoInstance.id)
    }
	
	def novo(){
		String json = request.JSON
		Gson gson = new Gson()
		def pedidoInstance = gson.fromJson(json, Pedido.class)
		//def slurper = new JsonSlurper()
		//def pedidoInstance = slurper.parseText(json)
	   
		for(itemPedido in pedidoInstance.itens) {
			itemPedido.item = Item.get(itemPedido.item.id)
		}
		pedidoInstance.codigo =1
		if (!pedidoInstance.save(flush: true)) {
			render(view: "create", model: [pedidoInstance: pedidoInstance])
			return
		}
		JSON.use('deep')
		render(pedidoInstance as JSON)
		
	}

    def show(Long id) {
        def pedidoInstance = Pedido.get(id)
        if (!pedidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedido.label', default: 'Pedido'), id])
            redirect(action: "list")
            return
        }

        [pedidoInstance: pedidoInstance]
    }

    def edit(Long id) {
        def pedidoInstance = Pedido.get(id)
        if (!pedidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedido.label', default: 'Pedido'), id])
            redirect(action: "list")
            return
        }

        [pedidoInstance: pedidoInstance]
    }

    def update(Long id, Long version) {
        def pedidoInstance = Pedido.get(id)
        if (!pedidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedido.label', default: 'Pedido'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pedidoInstance.version > version) {
                pedidoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'pedido.label', default: 'Pedido')] as Object[],
                          "Another user has updated this Pedido while you were editing")
                render(view: "edit", model: [pedidoInstance: pedidoInstance])
                return
            }
        }

        pedidoInstance.properties = params

        if (!pedidoInstance.save(flush: true)) {
            render(view: "edit", model: [pedidoInstance: pedidoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'pedido.label', default: 'Pedido'), pedidoInstance.id])
        redirect(action: "show", id: pedidoInstance.id)
    }

    def delete(Long id) {
        def pedidoInstance = Pedido.get(id)
        if (!pedidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedido.label', default: 'Pedido'), id])
            redirect(action: "list")
            return
        }

        try {
            pedidoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pedido.label', default: 'Pedido'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pedido.label', default: 'Pedido'), id])
            redirect(action: "show", id: id)
        }
    }
}
