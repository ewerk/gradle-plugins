package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.AnnotationProcessorPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * @author griffio
 */
class InitAnnotationProcessorGeneratedDir extends DefaultTask {

  private static final Logger LOG = Logging.getLogger(InitAnnotationProcessorGeneratedDir.class)

  InitAnnotationProcessorGeneratedDir() {
    this.group = AnnotationProcessorPlugin.TASK_GROUP
    this.description = "Creates the generated sources dir"
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  def createSourceFolders() {
    LOG.info("create source")
    project.file(project.annotationProcessor.sourcesDir).mkdirs()
  }
}
