package dk.nine.cloudconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rune Molin, rmo@nine.dk
 **/
@RestController
//@RefreshScope
public class Controller {
  @Value("${message}")
  private String message;

  @GetMapping(value = "/message", produces = MediaType.TEXT_PLAIN_VALUE)
  public String getMessage() {
    return message;
  }

}
