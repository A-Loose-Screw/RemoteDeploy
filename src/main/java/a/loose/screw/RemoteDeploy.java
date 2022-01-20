package a.loose.screw;

import org.gradle.api.Project;
import org.gradle.api.Plugin;

// Local
import a.loose.screw.logger.Logback;

/**
 * Greeting class plugin for gradle.
 * Creates a class called greeting.
 */
public class RemoteDeploy implements Plugin<Project> {
  public static Logback logback = new Logback("Core");
  public void apply(Project project) {
    project.getTasks().register("greeting", task -> {
      task.doLast(s -> logback.log("Hello from logger greeting"));
    });
  }
}