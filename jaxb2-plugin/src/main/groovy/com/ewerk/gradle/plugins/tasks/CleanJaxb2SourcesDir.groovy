package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.Jaxb2Plugin
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.tasks.TaskAction

import static org.gradle.api.logging.Logging.getLogger

/**
 * This task is responsible for purging the Jaxb2 sources dir.
 *
 * @author holgerstolzenberg
 * @since 1.0.1
 */
class CleanJaxb2SourcesDir extends DefaultTask {
  private static final Logger LOG = getLogger(CleanJaxb2SourcesDir.class)

  static final String DESCRIPTION = "Cleans the Jaxb2 sources dir."

  CleanJaxb2SourcesDir() {
    this.group = Jaxb2Plugin.TASK_GROUP
    this.description = DESCRIPTION
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  cleanSourceFolders() {
    LOG.info("Clean Jaxb2 source dir")

    Set<XjcTaskConfig> xjcConfigs = project.extensions.jaxb2.xjc

    for (XjcTaskConfig theConfig : xjcConfigs) {
      File generatedSourcesDir = project.file(theConfig.generatedSourcesDir)
      if (generatedSourcesDir.exists()) {
        generatedSourcesDir.deleteDir()
      }
    }
  }
}
