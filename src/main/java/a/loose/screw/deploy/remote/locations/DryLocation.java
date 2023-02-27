package a.loose.screw.deploy.remote.locations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

/**
 * Local Location for location type
 */
public class DryLocation implements Location {
  private Project _project;
  private RDLogger _logger;
  private String _name = "";

  public DryLocation(String name, Project project) {
    this._project = project;
    this._name = name;
    this._logger = RDLoggerFactory.getInstance().create(this._name + "Dry");
  }

  @Override
  public String getName() {
    return this._name;
  }

  @Override
  public void connect() throws Exception {
    // No need to connect
  }

  @Override
  public void disconnect() throws Exception {
    // No need to disconnect
  }

  @Override
  public boolean discover(String dst) throws Exception {
    File file = new File(dst);
    if (file.exists()) {
      return true;
    } else {
      try {
        file.mkdirs();
        return true;
      } catch (Exception e) {
        this._logger.errorHead("Could not create: " + dst);
        return false;
      }
    }
  }

  @Override
  public void put(File src, String dst) throws Exception {
    File file = new File(dst);
    try {
      if (this.discover(file.getParent())) {
        try {
          Files.copy(src.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
          this._logger.errorHead("File Copy Error");
          throw e;
        }
      } else {
        this._logger.error("Could not find " + src.getPath());
      }
    } catch (Exception e) {
      throw e;
    }
  }
}