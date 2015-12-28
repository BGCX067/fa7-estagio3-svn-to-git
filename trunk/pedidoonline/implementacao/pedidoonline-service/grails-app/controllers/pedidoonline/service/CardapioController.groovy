package pedidoonline.service

import grails.converters.JSON

import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.springframework.dao.DataIntegrityViolationException

import com.google.gson.Gson;

import br.com.pedidoonline.NoClassNameObjectMarshaller;

class CardapioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [cardapioInstanceList: Cardapio.list(params), cardapioInstanceTotal: Cardapio.count()]
    }

    def create() {
        [cardapioInstance: new Cardapio(params)]
    }

    def save() {
        def cardapioInstance = new Cardapio(params)
        if (!cardapioInstance.save(flush: true)) {
            render(view: "create", model: [cardapioInstance: cardapioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cardapio.label', default: 'Cardapio'), cardapioInstance.id])
        redirect(action: "show", id: cardapioInstance.id)
    }

    def show(Long id) {
        def cardapioInstance = Cardapio.get(id)
        if (!cardapioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardapio.label', default: 'Cardapio'), id])
            redirect(action: "list")
            return
        }

        [cardapioInstance: cardapioInstance]
    }
	
	def obter() {
		def cardapioInstance = Cardapio.getAll().get(0);
		JSON.use('deep')
		render (cardapioInstance as JSON);
	}

    def edit(Long id) {
        def cardapioInstance = Cardapio.get(id)
        if (!cardapioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardapio.label', default: 'Cardapio'), id])
            redirect(action: "list")
            return
        }

        [cardapioInstance: cardapioInstance]
    }

    def update(Long id, Long version) {
        def cardapioInstance = Cardapio.get(id)
        if (!cardapioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardapio.label', default: 'Cardapio'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cardapioInstance.version > version) {
                cardapioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cardapio.label', default: 'Cardapio')] as Object[],
                          "Another user has updated this Cardapio while you were editing")
                render(view: "edit", model: [cardapioInstance: cardapioInstance])
                return
            }
        }

        cardapioInstance.properties = params

        if (!cardapioInstance.save(flush: true)) {
            render(view: "edit", model: [cardapioInstance: cardapioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cardapio.label', default: 'Cardapio'), cardapioInstance.id])
        redirect(action: "show", id: cardapioInstance.id)
    }

    def delete(Long id) {
        def cardapioInstance = Cardapio.get(id)
        if (!cardapioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardapio.label', default: 'Cardapio'), id])
            redirect(action: "list")
            return
        }

        try {
            cardapioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cardapio.label', default: 'Cardapio'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cardapio.label', default: 'Cardapio'), id])
            redirect(action: "show", id: id)
        }
    }
}
