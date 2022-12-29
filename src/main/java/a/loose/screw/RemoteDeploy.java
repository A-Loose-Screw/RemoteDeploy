package a.loose.screw;

// Gradle
import org.gradle.api.Project;
import org.gradle.api.Plugin;

/**
 * Local
 */
import a.loose.screw.deploy.DeployPlugin;


/**
 * Greeting class plugin for gradle.
 * Creates a class called greeting.
 */
public class RemoteDeploy implements Plugin<Project> {
  public void apply(Project project) {
    project.getPluginManager().apply(DeployPlugin.class);
  }
}