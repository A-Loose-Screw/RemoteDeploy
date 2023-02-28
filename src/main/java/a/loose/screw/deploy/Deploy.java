package a.loose.screw.deploy;

import java.io.File;

import org.gradle.api.DefaultTask;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.tasks.Input;

import a.loose.screw.deploy.artifacts.Artifact;
import a.loose.screw.deploy.remote.Target;
import a.loose.screw.deploy.remote.locations.Location;
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class Deploy extends DefaultTask {

  private RDLogger _logger;

  public Deploy() {
    this._logger = RDLoggerFactory.getInstance().create("Deploy");
  }

  // Deploy artifacts at some point, or make artifacts deploy
  public void deploy(Target target, Artifact artifact) throws Exception {
    artifact.deploy(target);
  }
}