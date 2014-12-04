package ch.pantas.billsplitter.server

import ch.pantas.billsplitter.server.controller.EventController
import org.springframework.web.bind.annotation.RequestMapping
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

public class EventControllerTest extends Specification {

    private EventController controller;

    def setup() {
        this.controller = new EventController();
    }

    @Unroll("Method #methodName is mapped to URL #urlPattern and http method #httpMethod")
    def "assert all known REST URL's are mapped"() {
        expect:
        controller.class.getAnnotation(RequestMapping).value() == ["/groups"]
        controller.class.getMethod(methodName, methodParams as Class[]).getAnnotation(RequestMapping).value() == [urlPattern]
        controller.class.getMethod(methodName, methodParams as Class[]).getAnnotation(RequestMapping).method() == [httpMethod]
        where:
        methodName    | methodParams | urlPattern | httpMethod
        'home'        | []           | ""         | GET
        'importGroup' | []           | ""         | POST
    }
}
