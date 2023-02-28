package a.loose.screw.deploy.artifacts;

import java.io.File;

import javax.inject.Inject;

import org.gradle.api.Project;

import a.loose.screw.deploy.remote.locations.Location;

public class FileArtifact extends Artifact {
  private Project _project;
  private String _name = "";

  @Inject
  public FileArtifact(String name, Project project) {
    super(name);
    this._project = project;
    this._name = name;
  }

  @Override
  public String getName() {
    return this._name;
  }

  @Override
  public void artifactDeploy(Location location, String path) throws Exception {
    location.put(this.file, path);
  }

  // Artifact Specifics
  public File file;
}
