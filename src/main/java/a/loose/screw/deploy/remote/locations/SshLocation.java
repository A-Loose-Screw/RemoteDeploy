package a.loose.screw.deploy.remote.locations;

import java.io.File;
import java.util.Map;

import javax.inject.Inject;

import org.gradle.api.Project;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;
import groovyjarjarantlr4.v4.parse.GrammarTreeVisitor.channelSpec_return;

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

  @Inject
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
      this._session = jsch.getSession(this.user, this.address, this.port);
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
    this._logger.warn("Disconnecting -> " + this.address);
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
  public boolean discover(String dst) throws Exception {
    SftpATTRS attrs = null;

    try {
      attrs = this._channel.stat(dst);
    } catch (Exception e) {
      this._logger.debug(e.toString());
    }

    if (attrs != null) {
      return true;
    } else {
      try {
        this._channel.mkdir(dst);
        return true;
      } catch (Exception e) {
        this._logger.errorHead("Could not create: " + dst);
        return false;
      }
    }
  }

  @Override
  public void put(File src, String dst) throws Exception {
    try {
      if (this._channel.isConnected() && src.exists()) {
        if (discover(dst)) {
          this._channel.put(src.getPath(), dst);
        }
      } else {
        this._logger.error("Could not find " + src.getPath());
      }
    } catch (Exception e) {
      this._logger.errorHead("File Send Error");
      throw e;
    }
    this._logger.log("cp -> [" + src.getName() + " -> " + dst + "]");
  }

  public String address = "";
  public String user = "";
  public String password = "";
  public int port = 22;
}
