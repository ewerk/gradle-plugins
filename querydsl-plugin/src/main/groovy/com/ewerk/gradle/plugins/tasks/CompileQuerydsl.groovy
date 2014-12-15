package com.ewerk.gradle.plugins.tasks

import org.gradle.api.tasks.compile.JavaCompile

/**
 * Compiles the JPA meta model using querydsl JPA annotation processor.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class CompileQuerydsl extends JavaCompile {

  CompileQuerydsl() {
    setSource(project.sourceSets.main.java)
    setClasspath(project.configurations.compile)

    File file = project.file(project.querydsl.querydslSourcesDir);
    setDestinationDir(file)

    options.compilerArgs += [
        "-proc:only",
        "-processor", "com.mysema.query.apt.jpa.JPAAnnotationProcessor"
    ]
  }
}
