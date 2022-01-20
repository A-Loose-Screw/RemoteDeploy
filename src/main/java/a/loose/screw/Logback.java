package a.loose.screw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logback {
  private static final Logger LOGGER = LoggerFactory.getLogger(Logback.class);
  
  public static void print() {
    LOGGER.info("This is an INFO level message!");
    LOGGER.error("This is an ERROR level message!");
  }
}
