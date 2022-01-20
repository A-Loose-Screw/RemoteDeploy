package a.loose.screw.logger;

// gradle/logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logback {
  private static Logger _logger;
  private String _name;
  private boolean _noPrint;

  public Logback(String name) {
    _logger = LoggerFactory.getLogger(Logback.class);
    setName(name);
  }

  public Logback(String name, Logger logger) {
    _logger = logger;
    setName(name);
  }

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public boolean getNoPrint() {
    return _noPrint;
  }

  public void setNoPrint(boolean noPrint) {
    _noPrint = noPrint;
  }

  public static final Logger getLogger() {
    return _logger;
  }

  public void log(String msg) {
    if (!_noPrint) System.out.println("[" + _name + "] : " + msg);
    if (_logger.isInfoEnabled()) {
      _logger.info("[" + _name + "] : " + msg);
    }
  }

  public void info(String msg) {
    _logger.info(msg);
  }

  public void debug(String msg) {
    _logger.debug(msg);
  }

  public void error(String msg, Object t) {
    _logger.error(msg, t);
  }

  public void error(String msg) {
    _logger.error(msg);
  }

}
