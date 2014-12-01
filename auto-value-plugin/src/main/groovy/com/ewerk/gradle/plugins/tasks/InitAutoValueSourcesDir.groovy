package com.ewerk.gradle.plugins.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * This task is responsible for removing and recreating the configured auto-value source roots.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class InitAutoValueSourcesDir extends DefaultTask {

  private static final Logger LOG = Logging.getLogger(InitAutoValueSourcesDir.class)

  static final String GROUP = "Code generation"
  static final String DESCRIPTION = "Creates the Auto-value sources dir."

  InitAutoValueSourcesDir() {
    this.group = GROUP
    this.description = DESCRIPTION
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  def createSourceFolders() {

    boolean delete = project.extensions.autoValue.delete

    project.sourceSets.generated.java.srcDirs.each { dir ->
      if (delete && dir.exists()) {
        LOG.info("Remove: {}", dir);
        dir.delete()
      }

      LOG.info("Create: {}", dir);
      dir.mkdirs()
    }
  }
}