package kg.gov.tunduk.xroad;

import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;

import java.util.List;

public class XRoadConfiguration extends WsConfigurerAdapter {
    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(new XRoadEndpointInterceptor());
        super.addInterceptors(interceptors);
    }
}
