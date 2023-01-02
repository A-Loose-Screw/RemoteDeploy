package a.loose.screw.deploy.remote.location;

import org.gradle.api.Named;
import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class SshLocation implements Named {
  private Project _project;
  private RDLogger _logger;
  private String _name;

  public SshLocation(Project project) {
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create("SSH");
  }

  @Override
  public String getName() {
    return this._name;
  }

  public String host;
  public String user;
  public String pass;
}
