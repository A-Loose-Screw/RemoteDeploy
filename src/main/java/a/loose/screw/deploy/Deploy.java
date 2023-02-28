package a.loose.screw.deploy;

import java.io.File;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;
import org.gradle.api.tasks.Input;

import a.loose.screw.deploy.artifacts.Artifact;
import a.loose.screw.deploy.remote.Target;
import a.loose.screw.deploy.remote.locations.Location;
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class Deploy extends DefaultTask {

  private RDLogger _logger;
  private Project _project;

  @Inject
  public Deploy(Project project) {
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create("Deploy");
  }

  // Deploy artifacts at some point, or make artifacts deploy
  public void deploy(Target target, NamedDomainObjectContainer<Artifact> artifacts) throws Exception {
    NamedDomainObjectContainer<Artifact> targetArtifacts = this._project.container(Artifact.class);

    // Add target artifacts
    artifacts.forEach(artifact -> {
      for (String targetName : artifact.targets) {
        if (targetName.toLowerCase().equals(target.getName().toLowerCase())) {
          targetArtifacts.add(artifact);
        }
      }
    });

    if (targetArtifacts.isEmpty()) {
      this._logger.warn("No Artifacts to deploy for `" + target.getName() + "`");
      return;
    }

    try {
      target.getLocationsExtension().getLocations().forEach(location -> {
        try {
          // connect session
          location.connect();

          // Deploy artifacts
          targetArtifacts.forEach(targetArtifact -> {
            try {
              targetArtifact.deploy(target, location);
            } catch (Exception e) {
              this._logger.errorHead("Artifact error");
              this._logger.error(e.getMessage());
            }
          });

          // Disconnect session
          location.disconnect();
        } catch (Exception e) {
          this._logger.errorHead("Location error");
          this._logger.error(e.getMessage());
        }
      });
    } catch (Exception e) {
      throw e;
    }
  }
}