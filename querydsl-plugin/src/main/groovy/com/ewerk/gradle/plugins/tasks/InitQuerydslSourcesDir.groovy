package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.QuerydslPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction

/**
 * This task is responsible for removing and recreating the configured querydsl source roots.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class InitQuerydslSourcesDir extends DefaultTask {

  private static final Logger LOG = Logging.getLogger(InitQuerydslSourcesDir.class)

  static final String DESCRIPTION = "Creates the Querydsl sources dir."

  InitQuerydslSourcesDir() {
    this.group = QuerydslPlugin.TASK_GROUP
    this.description = DESCRIPTION
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  def createSourceFolders() {
    project.file(project.querydsl.querydslSourcesDir).mkdirs()
  }
}