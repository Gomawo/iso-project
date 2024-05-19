package miniproject

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ChecksumServiceSpec extends Specification {

    ChecksumService checksumService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Checksum(...).save(flush: true, failOnError: true)
        //new Checksum(...).save(flush: true, failOnError: true)
        //Checksum checksum = new Checksum(...).save(flush: true, failOnError: true)
        //new Checksum(...).save(flush: true, failOnError: true)
        //new Checksum(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //checksum.id
    }

    void "test get"() {
        setupData()

        expect:
        checksumService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Checksum> checksumList = checksumService.list(max: 2, offset: 2)

        then:
        checksumList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        checksumService.count() == 5
    }

    void "test delete"() {
        Long checksumId = setupData()

        expect:
        checksumService.count() == 5

        when:
        checksumService.delete(checksumId)
        sessionFactory.currentSession.flush()

        then:
        checksumService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Checksum checksum = new Checksum()
        checksumService.save(checksum)

        then:
        checksum.id != null
    }
}
