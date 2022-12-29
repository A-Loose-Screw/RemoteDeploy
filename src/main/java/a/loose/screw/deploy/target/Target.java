package a.loose.screw.deploy.target;

import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

import javax.inject.Inject;

import org.gradle.api.Named;

public class Target implements Named {
  private String _name;
  private Project _project;
  private RDLogger _logger;

  @Inject
  public Target(String name, Project project) {
    this._name = name;
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create("Target");
    this._logger.log(this._name + " created");
  }

  @Override
  public String getName() {
    return _name;
  }


  public String host;

}
