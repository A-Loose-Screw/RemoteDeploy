package a.loose.screw.logging;

// Gradle
import org.gradle.api.Project;
import org.gradle.internal.logging.text.StyledTextOutput.Style;

public class LogTest {
  public LogTest(Project project) {
    project.getTasks().register("logTest", task -> {
      task.doLast(s -> {
        RDLoggerFactory.getInstance().setColourOutput(project);
        RDLogger _logger = RDLoggerFactory.getInstance().create("RD-Core");
        _logger.error("Test Error");
        _logger.errorHead("Test Error Head");
        _logger.log("Test with style (Error)", Style.Failure);
        _logger.success("Test Success");
        _logger.warn("Test (warn/progress)");
      });
    });
  }
}