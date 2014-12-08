package ch.pantas.billsplitter.server

import org.springframework.context.annotation.PropertySource
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import spock.lang.Specification

import javax.transaction.Transactional

@ContextConfiguration(classes = TestApplication.class, loader = AnnotationConfigContextLoader.class)
@PropertySource("classpath:application.properties")
@Transactional
public class SpringTestBase extends Specification {
}