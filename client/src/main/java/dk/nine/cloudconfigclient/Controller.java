package dk.nine.cloudconfigclient;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rune Molin, rmo@nine.dk
 **/
@RestController
public class Controller {
  private final ExampleConfiguration exampleConfiguration;

  public Controller(ExampleConfiguration exampleConfiguration) {
    this.exampleConfiguration = exampleConfiguration;
  }

  @GetMapping(value = "/message", produces = MediaType.TEXT_PLAIN_VALUE)
  public String getMessage() {
    return exampleConfiguration.getMessage();
  }

}
