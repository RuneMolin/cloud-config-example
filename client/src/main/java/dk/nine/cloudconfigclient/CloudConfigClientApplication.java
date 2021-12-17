package dk.nine.cloudconfigclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CloudConfigClientApplication {
  private static final Logger logger = LoggerFactory.getLogger(CloudConfigClientApplication.class);

  @Value("${server.port}")
  private int serverPort;

  public static void main(String[] args) {
    SpringApplication.run(CloudConfigClientApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void notifyReadiness() {
    logger.info("****************************");
    logger.info("Client is ready");
    logger.info("Now try GET http://localhost:{}/message", serverPort);
    logger.info("****************************");
  }

}
