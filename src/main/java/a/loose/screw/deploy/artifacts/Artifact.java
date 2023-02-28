package a.loose.screw.deploy.artifacts;

import java.util.ArrayList;
import java.util.List;

import org.gradle.api.Named;
import org.gradle.api.Project;

import a.loose.screw.deploy.remote.Target;
import a.loose.screw.deploy.remote.locations.Location;
import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

/**
 * Main abstract class for artifact
 */
public abstract class Artifact implements Named {
  protected RDLogger _logger;
  public Artifact(String name) {
    this._logger = RDLoggerFactory.getInstance().create(name + "Artifact");
  }

  public String directory = "";
  public List<String> targets = new ArrayList<String>();

  public String[] preDeploy = {};
  public String[] postDeploy = {};

  public Boolean disabled = false;

  public abstract void artifactDeploy(Location location, String path) throws Exception;

  public void deploy(Target target, Location location) throws Exception {
    if (disabled) return;

    // Fix up pathing
    String rootDir = target.getDirectory();
    if (rootDir.length() > 0) {
      if (rootDir.substring(rootDir.length()-1) == "/" || rootDir.substring(rootDir.length()-1) == "\\") {
        rootDir = rootDir.substring(0, rootDir.length()-2);
      }
    }

    String localDir = this.directory;
    if (localDir.length() > 0) {
      if (localDir.substring(0) == "/" || localDir.substring(0) == "\\") {
        localDir = localDir.substring(1, localDir.length()-1);
      }
    }

    String path = rootDir + "/" + localDir;

    try {
      artifactDeploy(location, path);
    } catch (Exception e) {
      throw e;
    }
  };
}