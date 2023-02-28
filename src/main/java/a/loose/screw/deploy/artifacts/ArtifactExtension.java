package a.loose.screw.deploy.artifacts;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

import a.loose.screw.logging.RDLogger;
import a.loose.screw.logging.RDLoggerFactory;

public class ArtifactExtension {
  private Project _project;
  private RDLogger _logger;

  private NamedDomainObjectContainer<Artifact> _artifacts;

  public ArtifactExtension(Project project) {
    this._project = project;
    this._logger = RDLoggerFactory.getInstance().create("Components");
    this._artifacts = project.container(Artifact.class);
  }

  public FileArtifact fileArtifact(String name, Class<? extends FileArtifact> type, final Action<? super FileArtifact> config) {
    FileArtifact artifact = this._project.getObjects().newInstance(type, name, this._project);
    config.execute(artifact);
    this._artifacts.add(artifact);
    return artifact;
  }

  public FileArtifact fileArtifact(String name, final Action<? super FileArtifact> config) {
    return fileArtifact(name, FileArtifact.class, config);
  }

  public NamedDomainObjectContainer<Artifact> getArtifacts() {
    return this._artifacts;
  }
}