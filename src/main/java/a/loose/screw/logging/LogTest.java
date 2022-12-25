package a.loose.screw.logging;

// Gradle
import org.gradle.api.Project;
import org.gradle.internal.logging.text.StyledTextOutput.Style;

public class LogTest {
  public LogTest(Project project) {
    project.getTasks().register("logTest", task -> {
      task.doLast(s -> {
        RDLogger _logger = RDLoggerFactory.getInstance().create("RD-Core");
        RDLoggerFactory.getInstance().setColourOutput(project);
        _logger.error("Test Error");
        _logger.errorHead("Test Error Head");
        _logger.log("Test with style (Error)", Style.Failure);
        _logger.success("Test Successs");
        _logger.warn("Test (warn/progress)");
      });
    });
  }
}