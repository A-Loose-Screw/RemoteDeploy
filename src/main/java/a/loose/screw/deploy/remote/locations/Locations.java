package a.loose.screw.deploy.remote.locations;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

public class Locations {
  private String _name;
  private Project _project;

  // Locations
  private NamedDomainObjectContainer<Location> _locations;

  @Inject
  public Locations(String name, Project project) {
    this._project = project;
    this._name = name;
    this._locations = project.container(Location.class);
  }

  public SshLocation ssh(String name, final Action<? super SshLocation> config) {
    SshLocation location = this._project.getObjects().newInstance(SshLocation.class, name);
    config.execute(location);
    this._locations.add(location);
    return location;
  }

  public SshLocation ssh(final Action<? super SshLocation> config) {
    return ssh(this._name, config);
  }

  public NamedDomainObjectContainer<Location> getLocations() {
    return this._locations;
  }
}