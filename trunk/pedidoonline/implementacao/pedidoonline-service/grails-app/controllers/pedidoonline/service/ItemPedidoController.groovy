package pedidoonline.service

import org.springframework.dao.DataIntegrityViolationException

class ItemPedidoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [itemPedidoInstanceList: ItemPedido.list(params), itemPedidoInstanceTotal: ItemPedido.count()]
    }

    def create() {
        [itemPedidoInstance: new ItemPedido(params)]
    }

    def save() {
        def itemPedidoInstance = new ItemPedido(params)
        if (!itemPedidoInstance.save(flush: true)) {
            render(view: "create", model: [itemPedidoInstance: itemPedidoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'itemPedido.label', default: 'ItemPedido'), itemPedidoInstance.id])
        redirect(action: "show", id: itemPedidoInstance.id)
    }

    def show(Long id) {
        def itemPedidoInstance = ItemPedido.get(id)
        if (!itemPedidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemPedido.label', default: 'ItemPedido'), id])
            redirect(action: "list")
            return
        }

        [itemPedidoInstance: itemPedidoInstance]
    }

    def edit(Long id) {
        def itemPedidoInstance = ItemPedido.get(id)
        if (!itemPedidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemPedido.label', default: 'ItemPedido'), id])
            redirect(action: "list")
            return
        }

        [itemPedidoInstance: itemPedidoInstance]
    }

    def update(Long id, Long version) {
        def itemPedidoInstance = ItemPedido.get(id)
        if (!itemPedidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemPedido.label', default: 'ItemPedido'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (itemPedidoInstance.version > version) {
                itemPedidoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'itemPedido.label', default: 'ItemPedido')] as Object[],
                          "Another user has updated this ItemPedido while you were editing")
                render(view: "edit", model: [itemPedidoInstance: itemPedidoInstance])
                return
            }
        }

        itemPedidoInstance.properties = params

        if (!itemPedidoInstance.save(flush: true)) {
            render(view: "edit", model: [itemPedidoInstance: itemPedidoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'itemPedido.label', default: 'ItemPedido'), itemPedidoInstance.id])
        redirect(action: "show", id: itemPedidoInstance.id)
    }

    def delete(Long id) {
        def itemPedidoInstance = ItemPedido.get(id)
        if (!itemPedidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemPedido.label', default: 'ItemPedido'), id])
            redirect(action: "list")
            return
        }

        try {
            itemPedidoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'itemPedido.label', default: 'ItemPedido'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'itemPedido.label', default: 'ItemPedido'), id])
            redirect(action: "show", id: id)
        }
    }
}
