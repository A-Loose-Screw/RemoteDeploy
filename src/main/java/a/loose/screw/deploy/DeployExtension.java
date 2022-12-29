package a.loose.screw.deploy;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionAware;

import a.loose.screw.deploy.target.Target;
import a.loose.screw.deploy.target.TargetsExtension;
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class DeployExtension {
  // private Target _target;
  private TargetsExtension _targets;
  private Project _project;
  private RDLogger _logger;

  @Inject
  public DeployExtension(Project project) {
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create(this.toString());
    // this._targets = new TargetsExtension(project);

    this._targets = ((ExtensionAware)this).getExtensions().create("targets", TargetsExtension.class, project);
  }
}