package a.loose.screw.deploy;

import org.gradle.api.Action;
import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;

public class DeployExtension {
  private Target _target;
  private Project _project;
  private RDLogger _logger;

  public DeployExtension(Project project, RDLogger logger) {
    this._project = project;
    this._logger = logger;
  }

  public Target target(String name, Class<? extends Target> type, final Action<? extends Target> config) {
    // Target target = _project.getObjects().newInstance(type, name, this._project);
    Target target = _project.getObjects().newInstance(type);

    this._logger.log(name);
    
    // config.execute(target);
    this._target = target;
    return target;
  }

  public Target target(String name, final Action<? extends Target> config) {
    return target(name, Target.class, config);
  }
}