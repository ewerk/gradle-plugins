package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.AnnotationProcessorPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * @author griffio
 */
class CleanAnnotationProcessorGeneratedDir extends DefaultTask {

  private static final Logger LOG = Logging.getLogger(CleanAnnotationProcessorGeneratedDir.class)

  CleanAnnotationProcessorGeneratedDir() {
    this.group = AnnotationProcessorPlugin.TASK_GROUP
    this.description = "Cleans the sources dir."
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  cleanSourceFolders() {
    LOG.info("clean source")
    project.sourceSets.annotationProcessor.java.srcDirs.each { dir ->
      if (dir.exists()) {
        dir.deleteDir()
      }
    }
  }
}
