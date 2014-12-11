package com.ewerk.gradle.plugins.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * This task is responsible for purging the auto-value sources dir.
 *
 * @author holgerstolzenberg
 * @since 1.0.1
 */
class CleanAutoValueSourcesDir extends DefaultTask {

  private static final Logger LOG = Logging.getLogger(CleanAutoValueSourcesDir.class)

  static final String GROUP = "Code generation"
  static final String DESCRIPTION = "Cleans the Auto-value sources dir."

  CleanAutoValueSourcesDir() {
    this.group = GROUP
    this.description = DESCRIPTION
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  def cleanSourceFolders() {
    LOG.info("Clean Auto-Value source dir")

    project.sourceSets.autoValue.java.srcDirs.each { dir ->
      if (dir.exists()) {
        dir.deleteDir()
      }
    }
  }
}
