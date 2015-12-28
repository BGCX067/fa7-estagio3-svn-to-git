package pedidoonline.service

import org.springframework.dao.DataIntegrityViolationException

class FaturaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [faturaInstanceList: Fatura.list(params), faturaInstanceTotal: Fatura.count()]
    }

    def create() {
        [faturaInstance: new Fatura(params)]
    }

    def save() {
        def faturaInstance = new Fatura(params)
        if (!faturaInstance.save(flush: true)) {
            render(view: "create", model: [faturaInstance: faturaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fatura.label', default: 'Fatura'), faturaInstance.id])
        redirect(action: "show", id: faturaInstance.id)
    }

    def show(Long id) {
        def faturaInstance = Fatura.get(id)
        if (!faturaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fatura.label', default: 'Fatura'), id])
            redirect(action: "list")
            return
        }

        [faturaInstance: faturaInstance]
    }

    def edit(Long id) {
        def faturaInstance = Fatura.get(id)
        if (!faturaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fatura.label', default: 'Fatura'), id])
            redirect(action: "list")
            return
        }

        [faturaInstance: faturaInstance]
    }

    def update(Long id, Long version) {
        def faturaInstance = Fatura.get(id)
        if (!faturaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fatura.label', default: 'Fatura'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (faturaInstance.version > version) {
                faturaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'fatura.label', default: 'Fatura')] as Object[],
                          "Another user has updated this Fatura while you were editing")
                render(view: "edit", model: [faturaInstance: faturaInstance])
                return
            }
        }

        faturaInstance.properties = params

        if (!faturaInstance.save(flush: true)) {
            render(view: "edit", model: [faturaInstance: faturaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fatura.label', default: 'Fatura'), faturaInstance.id])
        redirect(action: "show", id: faturaInstance.id)
    }

    def delete(Long id) {
        def faturaInstance = Fatura.get(id)
        if (!faturaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fatura.label', default: 'Fatura'), id])
            redirect(action: "list")
            return
        }

        try {
            faturaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fatura.label', default: 'Fatura'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fatura.label', default: 'Fatura'), id])
            redirect(action: "show", id: id)
        }
    }
}
