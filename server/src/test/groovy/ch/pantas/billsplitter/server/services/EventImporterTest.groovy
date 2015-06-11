package ch.pantas.billsplitter.server.services

import ch.pantas.billsplitter.server.services.datatransfer.EventDto
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class EventImporterTest extends GroovyTestCase {

    private EventImporter eventImporter;

    void setUp() {
        eventImporter = new EventImporter();
    }

    void testSimpleDtoIsLoaded() {
        given:
        def eventDto = new EventDto()

        when:
        eventImporter.load(eventDto)

        then:
        fail()
    }
}
