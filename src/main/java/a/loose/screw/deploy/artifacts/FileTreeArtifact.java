package a.loose.screw.deploy.artifacts;


import org.gradle.api.Named;
import org.gradle.api.Project;
import org.gradle.api.file.FileTree;
import org.gradle.api.provider.Property;

public class FileTreeArtifact extends Artifact {
  private final Property<FileTree> _files;

  FileTreeArtifact(String name, Project project) {
    super(name, project);
    this._files = project.getObjects().property(FileTree.class);
  }

  void setFiles(FileTree tree) {
    this._files.set(tree);
  }
}