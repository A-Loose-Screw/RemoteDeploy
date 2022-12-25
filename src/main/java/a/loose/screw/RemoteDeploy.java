package a.loose.screw;

// Gradle
import org.gradle.api.Project;
import org.gradle.api.Plugin;

/**
 * Test
 */
public class RemoteDeploy implements Plugin<Project> {
  public void apply(Project project) {
    System.out.print("Test");
  }
}
