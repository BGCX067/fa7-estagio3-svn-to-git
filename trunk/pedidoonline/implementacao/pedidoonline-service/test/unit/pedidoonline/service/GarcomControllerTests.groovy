package pedidoonline.service



import org.junit.*
import grails.test.mixin.*

@TestFor(GarcomController)
@Mock(Garcom)
class GarcomControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/garcom/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.garcomInstanceList.size() == 0
        assert model.garcomInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.garcomInstance != null
    }

    void testSave() {
        controller.save()

        assert model.garcomInstance != null
        assert view == '/garcom/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/garcom/show/1'
        assert controller.flash.message != null
        assert Garcom.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/garcom/list'

        populateValidParams(params)
        def garcom = new Garcom(params)

        assert garcom.save() != null

        params.id = garcom.id

        def model = controller.show()

        assert model.garcomInstance == garcom
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/garcom/list'

        populateValidParams(params)
        def garcom = new Garcom(params)

        assert garcom.save() != null

        params.id = garcom.id

        def model = controller.edit()

        assert model.garcomInstance == garcom
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/garcom/list'

        response.reset()

        populateValidParams(params)
        def garcom = new Garcom(params)

        assert garcom.save() != null

        // test invalid parameters in update
        params.id = garcom.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/garcom/edit"
        assert model.garcomInstance != null

        garcom.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/garcom/show/$garcom.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        garcom.clearErrors()

        populateValidParams(params)
        params.id = garcom.id
        params.version = -1
        controller.update()

        assert view == "/garcom/edit"
        assert model.garcomInstance != null
        assert model.garcomInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/garcom/list'

        response.reset()

        populateValidParams(params)
        def garcom = new Garcom(params)

        assert garcom.save() != null
        assert Garcom.count() == 1

        params.id = garcom.id

        controller.delete()

        assert Garcom.count() == 0
        assert Garcom.get(garcom.id) == null
        assert response.redirectedUrl == '/garcom/list'
    }
}
