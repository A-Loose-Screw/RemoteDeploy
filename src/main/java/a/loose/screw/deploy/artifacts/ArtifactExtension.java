package a.loose.screw.deploy.artifacts;

import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class ArtifactExtension {
  private Project _project;
  private RDLogger _logger;

  public ArtifactExtension(Project project) {
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create("Components");
  }
}
