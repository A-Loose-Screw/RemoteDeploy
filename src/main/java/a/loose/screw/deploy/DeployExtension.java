package a.loose.screw.deploy;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;

public class DeployExtension {
  // private Target _target;
  private NamedDomainObjectContainer<Target> _targets;
  private Project _project;
  private RDLogger _logger;

  public DeployExtension(Project project, RDLogger logger) {
    this._project = project;
    this._logger = logger;
    this._targets = project.container(Target.class);
  }

  public Target target(String name, final Action<? super Target> config) {
    Target target = _targets.create(name);

    config.execute(target);
    this._targets.add(target);
    this._logger.log(name);
    return target;
  }

  public NamedDomainObjectContainer<Target> targets(Action<? super NamedDomainObjectContainer<Target>> config) {
    config.execute(this._targets);
    return this._targets;
  }
}