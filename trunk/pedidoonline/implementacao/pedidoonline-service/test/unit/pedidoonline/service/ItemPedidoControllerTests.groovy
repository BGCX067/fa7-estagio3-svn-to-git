package pedidoonline.service



import org.junit.*
import grails.test.mixin.*

@TestFor(ItemPedidoController)
@Mock(ItemPedido)
class ItemPedidoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/itemPedido/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.itemPedidoInstanceList.size() == 0
        assert model.itemPedidoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.itemPedidoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.itemPedidoInstance != null
        assert view == '/itemPedido/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/itemPedido/show/1'
        assert controller.flash.message != null
        assert ItemPedido.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/itemPedido/list'

        populateValidParams(params)
        def itemPedido = new ItemPedido(params)

        assert itemPedido.save() != null

        params.id = itemPedido.id

        def model = controller.show()

        assert model.itemPedidoInstance == itemPedido
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/itemPedido/list'

        populateValidParams(params)
        def itemPedido = new ItemPedido(params)

        assert itemPedido.save() != null

        params.id = itemPedido.id

        def model = controller.edit()

        assert model.itemPedidoInstance == itemPedido
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/itemPedido/list'

        response.reset()

        populateValidParams(params)
        def itemPedido = new ItemPedido(params)

        assert itemPedido.save() != null

        // test invalid parameters in update
        params.id = itemPedido.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/itemPedido/edit"
        assert model.itemPedidoInstance != null

        itemPedido.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/itemPedido/show/$itemPedido.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        itemPedido.clearErrors()

        populateValidParams(params)
        params.id = itemPedido.id
        params.version = -1
        controller.update()

        assert view == "/itemPedido/edit"
        assert model.itemPedidoInstance != null
        assert model.itemPedidoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/itemPedido/list'

        response.reset()

        populateValidParams(params)
        def itemPedido = new ItemPedido(params)

        assert itemPedido.save() != null
        assert ItemPedido.count() == 1

        params.id = itemPedido.id

        controller.delete()

        assert ItemPedido.count() == 0
        assert ItemPedido.get(itemPedido.id) == null
        assert response.redirectedUrl == '/itemPedido/list'
    }
}
