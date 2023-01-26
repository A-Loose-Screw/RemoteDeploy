package a.loose.screw.deploy.remote.locations;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.gradle.api.Named;

public interface Location extends Named {
  // public void put(Map<String, File> files) throws Exception;
  // public void put(Set<File> files) throws Exception;
  public void put(File src, String dst) throws Exception;
  public boolean discover(String dst) throws Exception;

  public void connect() throws Exception;
  public void disconnect() throws Exception;
  // public void command(String cmd) throws Exception;
}