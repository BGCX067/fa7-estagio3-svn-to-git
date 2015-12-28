package pedidoonline.service

import org.springframework.dao.DataIntegrityViolationException

class MesaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [mesaInstanceList: Mesa.list(params), mesaInstanceTotal: Mesa.count()]
    }

    def create() {
        [mesaInstance: new Mesa(params)]
    }

    def save() {
        def mesaInstance = new Mesa(params)
        if (!mesaInstance.save(flush: true)) {
            render(view: "create", model: [mesaInstance: mesaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'mesa.label', default: 'Mesa'), mesaInstance.id])
        redirect(action: "show", id: mesaInstance.id)
    }

    def show(Long id) {
        def mesaInstance = Mesa.get(id)
        if (!mesaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesa.label', default: 'Mesa'), id])
            redirect(action: "list")
            return
        }

        [mesaInstance: mesaInstance]
    }

    def edit(Long id) {
        def mesaInstance = Mesa.get(id)
        if (!mesaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesa.label', default: 'Mesa'), id])
            redirect(action: "list")
            return
        }

        [mesaInstance: mesaInstance]
    }

    def update(Long id, Long version) {
        def mesaInstance = Mesa.get(id)
        if (!mesaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesa.label', default: 'Mesa'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (mesaInstance.version > version) {
                mesaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'mesa.label', default: 'Mesa')] as Object[],
                          "Another user has updated this Mesa while you were editing")
                render(view: "edit", model: [mesaInstance: mesaInstance])
                return
            }
        }

        mesaInstance.properties = params

        if (!mesaInstance.save(flush: true)) {
            render(view: "edit", model: [mesaInstance: mesaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'mesa.label', default: 'Mesa'), mesaInstance.id])
        redirect(action: "show", id: mesaInstance.id)
    }

    def delete(Long id) {
        def mesaInstance = Mesa.get(id)
        if (!mesaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesa.label', default: 'Mesa'), id])
            redirect(action: "list")
            return
        }

        try {
            mesaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'mesa.label', default: 'Mesa'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'mesa.label', default: 'Mesa'), id])
            redirect(action: "show", id: id)
        }
    }
}
