package a.loose.screw.deploy.remote.locations;

import org.gradle.api.Project;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

/**
 * @todo implements location and provides the ssh location specifics
 */
public class SshLocation implements Location {
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

  @Override 
  public void deploy(String src, String dst) throws Exception {
    JSch jsch = new JSch();
    Session session = jsch.getSession(this.user, this.address, 22);

    session.setPassword(password);
    session.setConfig("StrictHostKeyChecking", "no");
    session.connect();

    ChannelSftp channel = (ChannelSftp)session.openChannel("sftp");
    channel.connect();
    channel.put(src, dst);
    channel.disconnect();
    session.disconnect();
  }

  public String address = "";
  public String user = "";
  public String password = "";
}
