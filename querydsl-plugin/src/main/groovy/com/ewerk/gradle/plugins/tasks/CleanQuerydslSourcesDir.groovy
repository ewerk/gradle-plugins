package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.QuerydslPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * This task is responsible for purging the 'querydsl' sources dir.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class CleanQuerydslSourcesDir extends DefaultTask {

  private static final Logger LOG = Logging.getLogger(CleanQuerydslSourcesDir.class)

  static final String DESCRIPTION = "Cleans the Querydsl sources dir."

  CleanQuerydslSourcesDir() {
    this.group = QuerydslPlugin.TASK_GROUP
    this.description = DESCRIPTION
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  def cleanSourceFolders() {
    LOG.info("Clean Querydsl source dir")

    project.sourceSets.querydsl.java.srcDirs.each { dir ->
      if (dir.exists()) {
        dir.deleteDir()
      }
    }
  }
}
