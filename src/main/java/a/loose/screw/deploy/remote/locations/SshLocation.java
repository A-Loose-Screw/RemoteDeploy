package a.loose.screw.deploy.remote.locations;

import java.io.File;
import java.util.Map;

import org.gradle.api.Project;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

/**
 * SSH Location for location type (address, )
 */
public class SshLocation implements Location {
  private Project _project;
  private RDLogger _logger;
  private String _name = "";

  // ssh connections
  private Session _session;
  private ChannelSftp _channel;

  public SshLocation(String name, Project project) {
    this._project = project;
    this._name = name;
    this._logger = RDLoggerFactory.getInstance().create(this._name + "Ssh");
  }

  @Override
  public String getName() {
    return this._name;
  }

  @Override
  public void connect() throws Exception {
    this._logger.success("Connecting -> " + this.address);
    try {
      JSch jsch = new JSch();
      this._session = jsch.getSession(this.user, this.address, 22);
      this._session.setPassword(this.password);
      this._session.setConfig("StrictHostKeyChecking", "no");
      this._session.connect(5000);

      ChannelSftp channel = (ChannelSftp)this._session.openChannel("sftp");
      this._channel = channel;
      this._channel.connect(5000);

    } catch (Exception e) {
      this._logger.errorHead("Connection Error");
      throw e;
    }
  }

  @Override
  public void disconnect() {
    try {
      this._channel.disconnect();
    } catch (Exception e) {
      this._logger.errorHead("Channel Disconnect Error");
      throw e;
    }

    try {
      this._session.disconnect();
    } catch (Exception e) {
      this._logger.errorHead("Session Disconnect Error");
      throw e;
    }
  }

  @Override
  public void put(File src, String dst) throws Exception {
    try {
      if (this._channel.isConnected() && src.exists()) {
        this._logger.log(src.getName() + " -> " + dst);
        this._channel.put(src.getPath(), dst);
      } else {
        this._logger.error("Could not send " + src.getName());
      }
    } catch (Exception e) {
      this._logger.errorHead("File Send Error");
      throw e;
    }
  }

  public String address = "";
  public String user = "";
  public String password = "";
}
