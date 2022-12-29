package a.loose.screw.deploy.target;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class TargetsExtension {
  private Project _project;
  private NamedDomainObjectContainer<Target> _targets;
  private RDLogger _logger;

  public TargetsExtension(Project project) {
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create("Targets");
    this._targets = project.container(Target.class);
  }

  public Target target(String name, final Action<? super Target> config) {
    Target target = _targets.create(name);
    config.execute(target);
    this._targets.add(target);
    return target;
  }
}