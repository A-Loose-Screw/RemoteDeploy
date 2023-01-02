package a.loose.screw.deploy.component;

import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class ComponentExtension {
  private Project _project;
  private RDLogger _logger;

  public ComponentExtension(Project project) {
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create("Components");
  }
}
