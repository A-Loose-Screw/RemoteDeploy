package a.loose.screw.deploy;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import org.gradle.language.base.plugins.ComponentModelBasePlugin;

public class RDPlugin implements Plugin<Project> {
  @Override
  public void apply(Project project) {
    project.getPluginManager().apply(ComponentModelBasePlugin.class);

    // @TODO add deploy extension
    // @TODO add targetdiscovery and deploy worker
  }
}
