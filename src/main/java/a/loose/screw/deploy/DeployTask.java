package a.loose.screw.deploy;

import java.nio.channels.Channel;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.ChannelSftp;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class DeployTask extends DefaultTask {
  private String host;
  private String username;
  private String password;
  private String sourcePath;
  private String destinationPath;

  public void deploy() throws Exception {
    JSch jsch = new JSch();
    Session session = jsch.getSession(username, host, 22);

    session.setPassword(password);
    session.setConfig("StrictHostKeyCHecking", "no");
    session.connect();

    ChannelSftp channel = (ChannelSftp)session.openChannel("sftp");
    channel.connect();
    channel.put(sourcePath, destinationPath);
    channel.disconnect();
    session.disconnect();
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setUsername(String user) {
    this.username = user;
  }

  public void setPassword(String pass) {
    this.password = pass;
  }

  public void setSourcePath(String src) {
    this.sourcePath = src;
  }

  public void setDestinationPath(String dest) {
    this.destinationPath = dest;
  }
}
