package pedidoonline.service



import org.junit.*
import grails.test.mixin.*

@TestFor(FaturaController)
@Mock(Fatura)
class FaturaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fatura/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.faturaInstanceList.size() == 0
        assert model.faturaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.faturaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.faturaInstance != null
        assert view == '/fatura/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fatura/show/1'
        assert controller.flash.message != null
        assert Fatura.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fatura/list'

        populateValidParams(params)
        def fatura = new Fatura(params)

        assert fatura.save() != null

        params.id = fatura.id

        def model = controller.show()

        assert model.faturaInstance == fatura
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fatura/list'

        populateValidParams(params)
        def fatura = new Fatura(params)

        assert fatura.save() != null

        params.id = fatura.id

        def model = controller.edit()

        assert model.faturaInstance == fatura
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fatura/list'

        response.reset()

        populateValidParams(params)
        def fatura = new Fatura(params)

        assert fatura.save() != null

        // test invalid parameters in update
        params.id = fatura.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fatura/edit"
        assert model.faturaInstance != null

        fatura.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fatura/show/$fatura.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fatura.clearErrors()

        populateValidParams(params)
        params.id = fatura.id
        params.version = -1
        controller.update()

        assert view == "/fatura/edit"
        assert model.faturaInstance != null
        assert model.faturaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fatura/list'

        response.reset()

        populateValidParams(params)
        def fatura = new Fatura(params)

        assert fatura.save() != null
        assert Fatura.count() == 1

        params.id = fatura.id

        controller.delete()

        assert Fatura.count() == 0
        assert Fatura.get(fatura.id) == null
        assert response.redirectedUrl == '/fatura/list'
    }
}
