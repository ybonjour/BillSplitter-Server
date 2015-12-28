package ch.pantas.billsplitter.server.services

import ch.pantas.billsplitter.server.persistence.EventImporter
import ch.pantas.billsplitter.server.services.datatransfer.EventDto

class EventImporterTest extends GroovyTestCase {

    private EventImporter eventImporter;

    void setUp() {
        eventImporter = new EventImporter();
    }

    void testSimpleDtoIsLoaded() {
        given:
        def eventDto = new EventDto()

        when:
        eventImporter.addEvent(eventDto)

        then:
        fail()
    }
}
