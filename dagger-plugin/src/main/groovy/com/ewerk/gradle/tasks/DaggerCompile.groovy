package com.ewerk.gradle.tasks

import org.gradle.api.plugins.WarPlugin
import org.gradle.api.tasks.compile.JavaCompile

/**
 * @author griffio
 */
class DaggerCompile extends JavaCompile {

  DaggerCompile() {

    setSource(project.sourceSets.main.java)

    if (project.plugins.hasPlugin(WarPlugin.class)) {
      project.configurations {
        dagger.extendsFrom compile, providedRuntime, providedCompile
      }
    } else {
      project.configurations {
        dagger.extendsFrom compile
      }
    }

    File file = project.file(project.dagger.daggerSourcesDir)

    options.compilerArgs = [
        "-proc:only",
        "-s", file.absolutePath,
        "-processor", project.dagger.PROCESSOR
    ]

    setClasspath(project.configurations.dagger)

    setDestinationDir(file)
  }
}
