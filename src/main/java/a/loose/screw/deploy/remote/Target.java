package a.loose.screw.deploy.remote;

import org.gradle.api.Project;

import a.loose.screw.deploy.remote.locations.Locations;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.Named;

public class Target implements Named {
  private String _name;
  private Locations _locations;

  @Inject
  public Target(String name, Project project) {
    this._name = name;
    this._locations = project.getObjects().newInstance(Locations.class, "locations", project);
  }

  public String rootDirectory = "";
  public Integer timeout = 3;

  @Override
  public String getName() {
    return _name;
  }

  public String getDirectory() {
    return rootDirectory;
  }
  
  public Integer getTimeout() {
    return timeout;
  }

  public void locations(final Action<? super Locations> config) {
    config.execute(this._locations);
  }

  public Locations getLocationsExtension() {
    return this._locations;
  }
}