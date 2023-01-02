package a.loose.screw.deploy.remote.location;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class LocationsExtension {
  private Project _project;
  private RDLogger _logger;
  private String _name;

  // Locations (ssh, ftp, etc...)
  private NamedDomainObjectContainer<SshLocation> _sshLocations;

  public LocationsExtension(String name, Project project) {
    this._project = project;
    this._name = name;
    this._logger = RDLoggerFactory.getInstance().create(this._name + "locations");
  }

  public String getName() {
    return _name;
  }

  public SshLocation ssh(final Action<? super SshLocation> config) {
    SshLocation location = _sshLocations.create(this._name + "ssh");
    config.execute(location);
    this._sshLocations.add(location);
    return location;
  }
}