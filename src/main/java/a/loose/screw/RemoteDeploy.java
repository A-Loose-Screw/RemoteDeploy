package a.loose.screw;

// Gradle
import org.gradle.api.Project;

import a.loose.screw.deploy.DeployTask;
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

import org.gradle.api.Plugin;

/**
 * Greeting class plugin for gradle.
 * Creates a class called greeting.
 */
public class RemoteDeploy implements Plugin<Project> {
  
  public void apply(Project project) {
    project.getTasks().register("greeting", task -> {
      task.doLast(s -> System.out.println("Hello from plugin"));
    });

    project.getTasks().register("deploy", DeployTask.class, task -> {
      task.setHost("192.168.0.1");
      task.setUsername("user");
      task.setPassword("password");
      task.setSourcePath("/path/to/source");
      task.setDestinationPath("/path/to/destination");
      try {
        task.deploy();
      } catch (Exception e) {
        // TODO: handle exception
        System.out.println(e);
      }
    });
  }
}