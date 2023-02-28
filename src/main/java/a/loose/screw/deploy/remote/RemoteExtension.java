package a.loose.screw.deploy.remote;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.internal.DefaultNamedDomainObjectSet;
import org.gradle.internal.reflect.DirectInstantiator;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class RemoteExtension extends DefaultNamedDomainObjectSet<Target> {
  private Project _project;
  private RDLogger _logger;

  public RemoteExtension(Project project) {
    super(Target.class, DirectInstantiator.INSTANCE, null);
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create("Remotes");
  }

  public Target target(String name, Class<? extends Target> type, final Action<? super Target> config) {
    Target target = this._project.getObjects().newInstance(type, name, this._project);
    config.execute(target);
    super.add(target);
    return target;
  }

  public Target target(String name, final Action<? super Target> config) {
    return target(name, Target.class, config);
  }

  public DefaultNamedDomainObjectSet<Target> getTargets() {
    return this;
  }
}