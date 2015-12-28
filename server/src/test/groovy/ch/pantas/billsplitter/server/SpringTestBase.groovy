package ch.pantas.billsplitter.server

import org.springframework.context.annotation.PropertySource
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import spock.lang.Specification

@ContextConfiguration(classes = TestApplication.class, loader = AnnotationConfigContextLoader.class)
@PropertySource("classpath:application.properties")
public class SpringTestBase extends Specification {

}