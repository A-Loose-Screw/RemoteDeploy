package a.loose.screw.deploy.remote;

import org.gradle.api.Project;

import a.loose.screw.deploy.remote.locations.Location;
import a.loose.screw.deploy.remote.locations.SshLocation;
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.Named;
import org.gradle.api.NamedDomainObjectContainer;

public class Target implements Named {
  private String _name;
  private Project _project;
  private RDLogger _logger;
  
  // Locations
  private NamedDomainObjectContainer<SshLocation> _sshLocation;

  @Inject
  public Target(String name, Project project) {
    this._name = name;
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create(name + "Target");

    this._sshLocation = this._project.container(SshLocation.class);
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
  
  public SshLocation ssh(String name, final Action<? super SshLocation> config) {
    SshLocation location = this._sshLocation.create(name);
    config.execute(location);
    this._sshLocation.add(location);
    return location;
  }

  public SshLocation ssh(final Action<? super SshLocation> config) {
    return ssh(this._name, config);
  }

}