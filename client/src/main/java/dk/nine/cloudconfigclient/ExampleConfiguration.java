package dk.nine.cloudconfigclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rune Molin, rmo@nine.dk
 **/
@Configuration
@ConfigurationProperties(prefix = "example")
public class ExampleConfiguration {
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
