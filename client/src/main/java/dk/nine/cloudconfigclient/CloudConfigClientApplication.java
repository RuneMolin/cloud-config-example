package dk.nine.cloudconfigclient;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CloudConfigClientApplication {
  @Value("${message}")
  private String message;

  public static void main(String[] args) {
    SpringApplication.run(CloudConfigClientApplication.class, args);
  }

  @GetMapping(value = "/message", produces = MediaType.TEXT_PLAIN_VALUE)
  public String getMessage() {
    return message;
  }
}
