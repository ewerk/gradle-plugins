package com.ewerk.gradle.plugins.tasks;

import org.gradle.api.tasks.compile.JavaCompile;

/**
 * Compiles the meta model using querydsl annotation processors supplied by the querydsl extension configuration
 * @author holgerstolzenberg , griffio
 * @since 1.0.3
 */
class QuerydslCompile extends JavaCompile {

  QuerydslCompile() {

    setSource(project.sourceSets.main.java)
    setClasspath(project.configurations.compile)

    File file = project.file(project.querydsl.querydslSourcesDir)
    setDestinationDir(file)

    options.compilerArgs += [
        "-proc:only",
        "-processor", project.querydsl.processors()
    ]
  }
}
