package pedidoonline.service

import org.springframework.dao.DataIntegrityViolationException

class GarcomController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [garcomInstanceList: Garcom.list(params), garcomInstanceTotal: Garcom.count()]
    }

    def create() {
        [garcomInstance: new Garcom(params)]
    }

    def save() {
        def garcomInstance = new Garcom(params)
        if (!garcomInstance.save(flush: true)) {
            render(view: "create", model: [garcomInstance: garcomInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'garcom.label', default: 'Garcom'), garcomInstance.id])
        redirect(action: "show", id: garcomInstance.id)
    }

    def show(Long id) {
        def garcomInstance = Garcom.get(id)
        if (!garcomInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'garcom.label', default: 'Garcom'), id])
            redirect(action: "list")
            return
        }

        [garcomInstance: garcomInstance]
    }

    def edit(Long id) {
        def garcomInstance = Garcom.get(id)
        if (!garcomInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'garcom.label', default: 'Garcom'), id])
            redirect(action: "list")
            return
        }

        [garcomInstance: garcomInstance]
    }

    def update(Long id, Long version) {
        def garcomInstance = Garcom.get(id)
        if (!garcomInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'garcom.label', default: 'Garcom'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (garcomInstance.version > version) {
                garcomInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'garcom.label', default: 'Garcom')] as Object[],
                          "Another user has updated this Garcom while you were editing")
                render(view: "edit", model: [garcomInstance: garcomInstance])
                return
            }
        }

        garcomInstance.properties = params

        if (!garcomInstance.save(flush: true)) {
            render(view: "edit", model: [garcomInstance: garcomInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'garcom.label', default: 'Garcom'), garcomInstance.id])
        redirect(action: "show", id: garcomInstance.id)
    }

    def delete(Long id) {
        def garcomInstance = Garcom.get(id)
        if (!garcomInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'garcom.label', default: 'Garcom'), id])
            redirect(action: "list")
            return
        }

        try {
            garcomInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'garcom.label', default: 'Garcom'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'garcom.label', default: 'Garcom'), id])
            redirect(action: "show", id: id)
        }
    }
}
