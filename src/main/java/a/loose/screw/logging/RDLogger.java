package a.loose.screw.logging;

import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.internal.logging.text.StyledTextOutput;
import org.gradle.internal.logging.text.StyledTextOutput.Style;

public class RDLogger {
  private static Logger _logger = Logging.getLogger("Log");
  private String _name;
  private boolean _quiet = false;
  private StyledTextOutput _styledOutput;

  /**
   * Contructor
   * For remote deploy logger
   */
  public RDLogger(String name) {
    _name = name;
    _logger = Logging.getLogger(_name);
  }

  /**
   * Contructor
   * For remote deploy logger with styled output
   */
  public RDLogger(String name, StyledTextOutput style) {
    _name = name;
    _styledOutput = style;
    _logger = Logging.getLogger(_name);
  }

  private String getStr(String msg) {
    return ("[" + _name + "]: " + msg);
  }

  public Logger getLogger() {
    return _logger;
  }

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  public void setStyle(StyledTextOutput.Style style) {
    _styledOutput.style(style);
  }

  public StyledTextOutput getStyledOutput() {
    return _styledOutput;
  }

  public void log(String msg) {
    if (!_quiet) {
      System.out.println("[" + _name + "]: " + msg);
    }

    if (_logger.isInfoEnabled()) {
      _logger.info("[" + _name + "]: " + msg);
    }
  }

  public void log(String msg, StyledTextOutput.Style style) {
    if (_styledOutput != null) {
      _styledOutput.withStyle(style).println(getStr(msg));
    } else {
      // System.out.print("Null Style");
      log(msg);
    }
  }

  public void info(String msg) {
    _logger.info(msg);
  }

  public void debug(String msg) {
    _logger.debug(msg);
  }

  public void error(String msg) {
    log(msg, Style.Failure);
  }

  public void errorHead(String msg) {
    log(msg, Style.FailureHeader);
  }

  public void success(String msg) {
    log(msg, Style.Success);
  }

  public void warn(String msg) {
    log(msg, Style.ProgressStatus);
  }

  public void progressStatus(String msg) {
    warn(msg);
  }

  public void setQuiet(boolean quiet) {
    _quiet = quiet;
  }
}