package biblioteca3

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class OperacionServiceSpec extends Specification {

    OperacionService operacionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Operacion(...).save(flush: true, failOnError: true)
        //new Operacion(...).save(flush: true, failOnError: true)
        //Operacion operacion = new Operacion(...).save(flush: true, failOnError: true)
        //new Operacion(...).save(flush: true, failOnError: true)
        //new Operacion(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //operacion.id
    }

    void "test get"() {
        setupData()

        expect:
        operacionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Operacion> operacionList = operacionService.list(max: 2, offset: 2)

        then:
        operacionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        operacionService.count() == 5
    }

    void "test delete"() {
        Long operacionId = setupData()

        expect:
        operacionService.count() == 5

        when:
        operacionService.delete(operacionId)
        sessionFactory.currentSession.flush()

        then:
        operacionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Operacion operacion = new Operacion()
        operacionService.save(operacion)

        then:
        operacion.id != null
    }
}
