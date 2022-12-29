package a.loose.screw.deploy;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

import a.loose.screw.deploy.target.Target;
import a.loose.screw.deploy.target.Targets;
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class DeployExtension {
  // private Target _target;
  private Targets _targets;
  private Project _project;
  private RDLogger _logger;

  @Inject
  public DeployExtension(Project project) {
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create(this.toString());
    this._targets = new Targets(project);
  }

  public Target target(String name, final Action<? super Target> config) {
    return this._targets.target(name, config);
  }

  public NamedDomainObjectContainer<Target> targets(Action<? super NamedDomainObjectContainer<Target>> config) {
    return this._targets.targets(config);
  }
}