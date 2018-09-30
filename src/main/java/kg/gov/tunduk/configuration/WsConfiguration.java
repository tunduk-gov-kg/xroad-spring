package kg.gov.tunduk.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

@EnableWs
@Configuration
public class WsConfiguration extends WsConfigurerAdapter {

    @SuppressWarnings("unchecked")
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "hello")
    public XRoadWsdlDefinition defaultWsdl11Definition() {
        XRoadWsdlDefinition definition = new XRoadWsdlDefinition();
        definition.setPortTypeName("HelloPort");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace("http://tunduk.gov.kg");
        definition.setSchema(helloSchema());
        return definition;
    }

    @Bean
    public XsdSchema helloSchema() {
        return new SimpleXsdSchema(new ClassPathResource("hello.xsd"));
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(new XRoadEndpointInterceptor());
        super.addInterceptors(interceptors);
    }
}