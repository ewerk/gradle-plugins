package com.ewerk.gradle.plugins.tasks

import org.gradle.api.plugins.WarPlugin
import org.gradle.api.tasks.compile.JavaCompile

/**
 * @author griffio
 */
class AnnotationProcessorCompile extends JavaCompile {

  AnnotationProcessorCompile() {

    setSource(project.sourceSets.main.java)

    if (project.plugins.hasPlugin(WarPlugin.class)) {
      project.configurations {
        annotationProcessor.extendsFrom compile, providedRuntime, providedCompile
      }
    } else {
      project.configurations {
        annotationProcessor.extendsFrom compile
      }
    }

    setClasspath(project.configurations.annotationProcessor)

    project.afterEvaluate {

      File file = project.file(project.annotationProcessor.sourcesDir)

      setDestinationDir(file)

      options.compilerArgs = [
          "-proc:only",
          "-s", file.absolutePath,
          "-processor", project.annotationProcessor.processor
      ]
    }
  }
}
