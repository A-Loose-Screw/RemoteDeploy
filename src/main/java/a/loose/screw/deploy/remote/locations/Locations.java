package a.loose.screw.deploy.remote.locations;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

public class Locations {
  private String _name;
  private Project _project;

  // Locations
  private NamedDomainObjectContainer<SshLocation> _sshLocations;

  @Inject
  public Locations(String name, Project project) {
    this._project = project;
    this._name = name;
    this._sshLocations = project.container(SshLocation.class);
  }

  public SshLocation ssh(String name, final Action<? super SshLocation> config) {
    SshLocation location = this._sshLocations.create(name);
    config.execute(location);
    this._sshLocations.add(location);
    return location;
  }

  public SshLocation ssh(final Action<? super SshLocation> config) {
    return ssh(this._name, config);
  }

  public NamedDomainObjectContainer<SshLocation> getSshLocations() {
    return this._sshLocations;
  }
}