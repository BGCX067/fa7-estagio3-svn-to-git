package pedidoonline.service



import org.junit.*
import grails.test.mixin.*

@TestFor(MesaController)
@Mock(Mesa)
class MesaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/mesa/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.mesaInstanceList.size() == 0
        assert model.mesaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.mesaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.mesaInstance != null
        assert view == '/mesa/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/mesa/show/1'
        assert controller.flash.message != null
        assert Mesa.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/mesa/list'

        populateValidParams(params)
        def mesa = new Mesa(params)

        assert mesa.save() != null

        params.id = mesa.id

        def model = controller.show()

        assert model.mesaInstance == mesa
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/mesa/list'

        populateValidParams(params)
        def mesa = new Mesa(params)

        assert mesa.save() != null

        params.id = mesa.id

        def model = controller.edit()

        assert model.mesaInstance == mesa
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/mesa/list'

        response.reset()

        populateValidParams(params)
        def mesa = new Mesa(params)

        assert mesa.save() != null

        // test invalid parameters in update
        params.id = mesa.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/mesa/edit"
        assert model.mesaInstance != null

        mesa.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/mesa/show/$mesa.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        mesa.clearErrors()

        populateValidParams(params)
        params.id = mesa.id
        params.version = -1
        controller.update()

        assert view == "/mesa/edit"
        assert model.mesaInstance != null
        assert model.mesaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/mesa/list'

        response.reset()

        populateValidParams(params)
        def mesa = new Mesa(params)

        assert mesa.save() != null
        assert Mesa.count() == 1

        params.id = mesa.id

        controller.delete()

        assert Mesa.count() == 0
        assert Mesa.get(mesa.id) == null
        assert response.redirectedUrl == '/mesa/list'
    }
}
