package com.ewerk.gradle.plugins.tasks

import org.gradle.api.tasks.compile.JavaCompile

/**
 * Compiles the JPA meta model using querydsl JPA annotation processor.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
abstract class AbstractCompileQuerydsl extends JavaCompile {

  AbstractCompileQuerydsl() {
    setSource(project.sourceSets.main.java)
    setClasspath(project.configurations.compile)

    File file = project.file(project.querydsl.querydslSourcesDir);
    setDestinationDir(file)

    options.compilerArgs += [
        "-proc:only",
        "-processor", processor()
    ]
  }

  abstract String processor()
}
