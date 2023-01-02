package a.loose.screw.deploy.remote;

import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionAware;

import a.loose.screw.deploy.remote.location.LocationsExtension;
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

import javax.inject.Inject;

import org.gradle.api.Named;

public class Target implements Named {
  private String _name;
  private Project _project;
  private RDLogger _logger;
  private LocationsExtension _locations;

  @Inject
  public Target(String name, Project project) {
    this._name = name;
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create("Target");
    this._locations = ((ExtensionAware)this).getExtensions().create("locations", LocationsExtension.class, name, this._project);
  }

  @Override
  public String getName() {
    return _name;
  }

  public String getDirectory() {
    return directory;
  }
  
  public Integer getTimeout() {
    return timeout;
  }

  public String directory = "";
  public Integer timeout = 3;
}