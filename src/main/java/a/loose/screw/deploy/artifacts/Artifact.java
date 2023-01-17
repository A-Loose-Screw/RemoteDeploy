package a.loose.screw.deploy.artifacts;

import org.gradle.api.Named;
import org.gradle.api.Project;

public class Artifact implements Named {
  private String _name;
  private Project _project;
  public Artifact(String name, Project project) {
    this._name = name;
    this._project = project;
  }

  @Override
  public String getName() {
    return this._name;
  }
}
