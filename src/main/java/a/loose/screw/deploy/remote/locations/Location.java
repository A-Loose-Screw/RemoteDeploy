package a.loose.screw.deploy.remote.locations;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class Location {
  private Project _project;
  private RDLogger _logger;
  private String _name;

  @Inject
  public Location(String name, Project project) {
    this._project = project;
    this._name = name;
  }
}