package a.loose.screw.deploy.remote.locations;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.gradle.api.Named;

// Main abstract class
public interface Location extends Named {
  public void put(File src, String dst) throws Exception;
  public boolean discover(String dst) throws Exception;

  public void connect() throws Exception;
  public void disconnect() throws Exception;
}