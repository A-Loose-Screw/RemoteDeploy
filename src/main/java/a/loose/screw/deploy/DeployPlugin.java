package a.loose.screw.deploy;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class DeployPlugin implements Plugin<Project> {
  private RDLogger _logger;

  public void apply(Project project) {
    RDLoggerFactory.getInstance().setColourOutput(project);
    this._logger = RDLoggerFactory.getInstance().create("RemoteDeploy");

    DeployExtension deployExtension = project.getExtensions().create("deploy", DeployExtension.class, project);

    project.getTasks().register("deploy", Deploy.class, deploy -> {
      project.getExtensions().getByType(DeployExtension.class).getRemoteExtension().getTargets().forEach(target -> {
        this._logger.log("Deploying " + target.getName() + " -> " + target.host);
      });
    });
  }
}
