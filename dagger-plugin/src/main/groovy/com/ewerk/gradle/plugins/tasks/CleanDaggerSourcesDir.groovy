package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.DaggerPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * @author griffio
 */
class CleanDaggerSourcesDir extends DefaultTask {

  private static final Logger LOG = Logging.getLogger(CleanDaggerSourcesDir.class)

  CleanDaggerSourcesDir() {
    this.group = DaggerPlugin.TASK_GROUP
    this.description = "Cleans the Dagger sources dir."
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  cleanSourceFolders() {
    LOG.info("clean Source")
    def daggerSourcesDir = DaggerPlugin.verifyNotWithinMainBuildSrc(project)
    if (daggerSourcesDir.exists()) {
      daggerSourcesDir.deleteDir()
    }
  }
}
