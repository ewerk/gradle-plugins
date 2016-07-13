package com.ewerk.gradle.plugins.tasks

import org.gradle.api.plugins.WarPlugin
import org.gradle.api.tasks.compile.JavaCompile
/**
 * Compiles the meta model using querydsl annotation processors supplied by the querydsl extension configuration
 * @author holgerstolzenberg , griffio
 * @since 1.0.3
 */
class QuerydslCompile extends JavaCompile {

  QuerydslCompile() {
    setSource(project.sourceSets.main.java)

    if (project.plugins.hasPlugin(WarPlugin.class)) {
      project.configurations {
        querydsl.extendsFrom compile, providedRuntime, providedCompile
      }
    } else {
      project.configurations {
        querydsl.extendsFrom compile
      }
    }

    project.afterEvaluate {
      setClasspath(project.configurations.querydsl)
      File file = project.file(project.querydsl.querydslSourcesDir)
      setDestinationDir(file)
    }
  }
}
