package com.ewerk.gradle.plugins.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * TODO holgerstolzenberg: Document
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class InitGeneratedSourceDir extends DefaultTask {

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  def createSourceFolders() {
    project.sourceSets.generated.java.srcDirs.each { dir ->
      if (dir.exists()) {
        dir.delete()
      }
      dir.mkdirs()
    }
  }
}