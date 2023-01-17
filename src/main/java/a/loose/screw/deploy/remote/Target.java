package a.loose.screw.deploy.remote;

import org.gradle.api.Project;
import org.gradle.api.internal.DefaultDomainObjectCollection;

import a.loose.screw.deploy.remote.locations.Location;
import a.loose.screw.deploy.remote.locations.Locations;
import a.loose.screw.deploy.remote.locations.SshLocation;
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
    this._logger = RDLoggerFactory.getInstance().create(name + "Target");

    this._locations = project.getObjects().newInstance(Locations.class, "locations", project);
  }

  public String directory = "";
  public Integer timeout = 3;

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

  public void locations(final Action<? super Locations> config) {
    config.execute(this._locations);
  }

}