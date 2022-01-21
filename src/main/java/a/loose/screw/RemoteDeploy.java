package a.loose.screw;

// Gradle
import org.gradle.api.Project;
import org.gradle.internal.logging.text.StyledTextOutput.Style;
import org.gradle.api.Plugin;

// Local
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

/**
 * Greeting class plugin for gradle.
 * Creates a class called greeting.
 */
public class RemoteDeploy implements Plugin<Project> {
  public void apply(Project project) {
    RDLoggerFactory.getInstance().setColourOutput(project);

    // project.getPluginManager().apply();
  }
}