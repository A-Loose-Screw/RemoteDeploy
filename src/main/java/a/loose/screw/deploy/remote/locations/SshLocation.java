package a.loose.screw.deploy.remote.locations;

import org.gradle.api.Named;
import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class SshLocation implements Named {
  private Project _project;
  private RDLogger _logger;
  private String _name = "";

  public SshLocation(String name, Project project) {
    this._project = project;
    this._name = name;
    this._logger = RDLoggerFactory.getInstance().create(this._name + "ssh");
    this._logger.log("created ssh location");
  }

  @Override
  public String getName() {
    return this._name;
  }

  public String address = "";
  public String user = "";
  public String password = "";
}
