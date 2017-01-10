package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.QuerydslPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * This task is responsible for removing and recreating the configured querydsl source roots.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class InitQuerydslSourcesDir extends DefaultTask {

  static final String DESCRIPTION = "Creates the Querydsl sources dir."

  InitQuerydslSourcesDir() {
    this.group = QuerydslPlugin.TASK_GROUP
    this.description = DESCRIPTION
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  createSourceFolders() {
    project.file(project.querydsl.querydslSourcesDir).mkdirs()
  }
}