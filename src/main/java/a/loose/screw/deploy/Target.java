package a.loose.screw.deploy;

import org.gradle.api.NamedDomainObjectContainer;

import javax.inject.Inject;

import org.gradle.api.Named;

public class Target implements Named {
  private String _name;

  @Inject
  public Target(String name) {
    this._name = name;
  }

  @Override
  public String getName() {
    return _name;
  }


  public String host;

}
