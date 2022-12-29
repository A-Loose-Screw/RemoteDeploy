package a.loose.screw;

// Gradle
import org.gradle.api.Project;

import a.loose.screw.deploy.Deploy;
import a.loose.screw.deploy.DeployExtension;
import a.loose.screw.deploy.DeployPlugin;
import a.loose.screw.deploy.target.Target;
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

import org.gradle.api.Plugin;

/**
 * Greeting class plugin for gradle.
 * Creates a class called greeting.
 */
public class RemoteDeploy implements Plugin<Project> {
  public void apply(Project project) {
    project.getPluginManager().apply(DeployPlugin.class);
  }
}