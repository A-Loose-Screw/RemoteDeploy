package a.loose.screw.logging;

import org.gradle.api.Project;
import org.gradle.api.internal.project.ProjectInternal;
import org.gradle.internal.logging.text.StyledTextOutput;
import org.gradle.internal.logging.text.StyledTextOutputFactory;

public class RDLoggerFactory {
  private static RDLoggerFactory _loggerFactory = new RDLoggerFactory();
  private StyledTextOutput _output = null;

  public static RDLoggerFactory getInstance() {
    return _loggerFactory;
  }

  public void setColourOutput(Project project) {
    StyledTextOutputFactory factory = ((ProjectInternal)project).getServices().get(StyledTextOutputFactory.class);
    _output = factory.create(this.getClass());
  }

  public RDLogger create(String name) {
    return new RDLogger(name, _output);
  }
}