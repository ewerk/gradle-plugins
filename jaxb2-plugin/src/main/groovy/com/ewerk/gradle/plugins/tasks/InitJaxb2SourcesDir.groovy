package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.Jaxb2Plugin
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.logging.Logger
import org.gradle.api.tasks.TaskAction

import static org.gradle.api.logging.Logging.getLogger

/**
 * This task is responsible for removing and recreating the configured Jaxb2 source roots.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class InitJaxb2SourcesDir extends DefaultTask {
  private static final Logger LOG = getLogger(InitJaxb2SourcesDir.class)

  static final String DESCRIPTION = "Creates the Jaxb2 sources dir."

  InitJaxb2SourcesDir() {
    this.group = Jaxb2Plugin.TASK_GROUP
    this.description = DESCRIPTION
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  createSourceFolders() {
    Set<XjcTaskConfig> xjcConfigs = project.extensions.jaxb2.xjc

    for (XjcTaskConfig theConfig : xjcConfigs) {
      File generatedSourcesDir = project.file(theConfig.generatedSourcesDir)
      if (theConfig.createSourceSet) {
        verifyNotWithinMainBuildSrc(generatedSourcesDir)
      }
      createSourcesDirectory(generatedSourcesDir)
    }
  }

  private static boolean createSourcesDirectory(generatedSourcesDir) {
    def created = generatedSourcesDir.exists()
    if (!created) {
      LOG.info("Create source set ${generatedSourcesDir}.")
      created = generatedSourcesDir.mkdirs()
    }
    return created
  }

  private void verifyNotWithinMainBuildSrc(generatedSourcesDir) {
    project.sourceSets.main.java.srcDirs.each { d ->
      if (d.absolutePath == generatedSourcesDir.absolutePath) {
        throw new GradleException("The configured generatedSourcesDir must specify a separate location to existing source code.")
      }
    }
  }
}
