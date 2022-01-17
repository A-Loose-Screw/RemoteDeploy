package a.loose.screw;

import org.gradle.api.Project;
import org.gradle.api.Plugin;

/**
 * Greeting class plugin for gradle.
 * Creates a class called greeting.
 */
public class RemoteDeploy implements Plugin<Project> {
  public void apply(Project project) {
    project.getTasks().register("greeting", task -> {
      task.doLast(s -> System.out.println("Hello from gradle plugin a.loose.screw.greeting"));
    });
  }
}