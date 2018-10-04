package kg.gov.tunduk.democonsumer.component.task;

import kg.gov.tunduk.democonsumer.gateway.HelloSoapClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleTask {

    private final HelloSoapClient client;
    private final Logger logger = LoggerFactory.getLogger(SampleTask.class);

    public SampleTask(HelloSoapClient client) {
        this.client = client;
    }

    @Scheduled(fixedDelay = 5000)
    public void execute() {
        String process = client.process("John Doe!");
        logger.info(process);
    }
}
