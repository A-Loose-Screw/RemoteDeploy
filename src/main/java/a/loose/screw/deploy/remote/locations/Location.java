package a.loose.screw.deploy.remote.locations;

import javax.inject.Inject;

import org.gradle.api.Action;
import org.gradle.api.Named;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

/**
 * Abstract Location used by artifacts. 
 * Specific location functions overridden by super classes
 */
public interface Location extends Named {
  public void deploy(String src, String dst) throws Exception;
}