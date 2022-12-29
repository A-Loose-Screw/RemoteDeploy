package a.loose.screw.deploy;

import javax.inject.Inject;

import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionAware;

import a.loose.screw.deploy.component.ComponentExtension;
import a.loose.screw.deploy.remote.RemoteExtension;
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class DeployExtension {
  // private Target _target;
  private RemoteExtension _targets;
  private ComponentExtension _componentExtension;
  private Project _project;
  private RDLogger _logger;

  @Inject
  public DeployExtension(Project project) {
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create(this.toString());

    // Extensions
    this._targets = ((ExtensionAware)this).getExtensions().create("remotes", RemoteExtension.class, project);
    this._componentExtension = ((ExtensionAware)this).getExtensions().create("components", ComponentExtension.class, project);
  }

  public RemoteExtension getRemoteExtension() {
    return this._targets;
  }
}