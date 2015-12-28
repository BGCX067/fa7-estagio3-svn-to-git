package pedidoonline.service



import org.junit.*
import grails.test.mixin.*

@TestFor(CardapioController)
@Mock(Cardapio)
class CardapioControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cardapio/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cardapioInstanceList.size() == 0
        assert model.cardapioInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cardapioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cardapioInstance != null
        assert view == '/cardapio/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cardapio/show/1'
        assert controller.flash.message != null
        assert Cardapio.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cardapio/list'

        populateValidParams(params)
        def cardapio = new Cardapio(params)

        assert cardapio.save() != null

        params.id = cardapio.id

        def model = controller.show()

        assert model.cardapioInstance == cardapio
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cardapio/list'

        populateValidParams(params)
        def cardapio = new Cardapio(params)

        assert cardapio.save() != null

        params.id = cardapio.id

        def model = controller.edit()

        assert model.cardapioInstance == cardapio
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cardapio/list'

        response.reset()

        populateValidParams(params)
        def cardapio = new Cardapio(params)

        assert cardapio.save() != null

        // test invalid parameters in update
        params.id = cardapio.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cardapio/edit"
        assert model.cardapioInstance != null

        cardapio.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cardapio/show/$cardapio.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cardapio.clearErrors()

        populateValidParams(params)
        params.id = cardapio.id
        params.version = -1
        controller.update()

        assert view == "/cardapio/edit"
        assert model.cardapioInstance != null
        assert model.cardapioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cardapio/list'

        response.reset()

        populateValidParams(params)
        def cardapio = new Cardapio(params)

        assert cardapio.save() != null
        assert Cardapio.count() == 1

        params.id = cardapio.id

        controller.delete()

        assert Cardapio.count() == 0
        assert Cardapio.get(cardapio.id) == null
        assert response.redirectedUrl == '/cardapio/list'
    }
}
