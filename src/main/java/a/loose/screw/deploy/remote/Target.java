package a.loose.screw.deploy.remote;

import org.gradle.api.Project;

import a.loose.screw.deploy.remote.location.Locations;
// import a.loose.screw.deploy.remote.location.LocationExtension;
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.Named;

public class Target implements Named {
  private String _name;
  private Project _project;
  private RDLogger _logger;
  private Locations _locations;

  @Inject
  public Target(String name, Project project) {
    this._name = name;
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create("Target");
  }

  @Override
  public String getName() {
    return _name;
  }

  public Locations locations(final Action<? super Locations> config) {
    Locations location = new Locations();
    config.execute(location);
    this._locations = location;
    return location;
  }

  // public String host;

}
