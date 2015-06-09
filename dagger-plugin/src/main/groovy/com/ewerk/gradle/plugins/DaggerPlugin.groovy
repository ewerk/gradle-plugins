package com.ewerk.gradle.plugins

import com.ewerk.gradle.tasks.CleanDaggerSourcesDir
import com.ewerk.gradle.tasks.DaggerCompile
import com.ewerk.gradle.tasks.InitDaggerSourcesDir
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.WarPlugin

/**
 * @author griffio
 *
 * Dagger2 (http://google.github.io/dagger/) is a library with a processor dependency (com.google.dagger:dagger-compiler)
 * and compile transitive dependency (com.google.dagger:dagger) that is required for the generated code.
 *
 * The processor dependency is added to a separate JavaCompile task.
 * Tasks in this plugin : compileDagger, cleanDaggerSourcesDir, initDaggerSourcesDir
 *
 */
public class DaggerPlugin implements Plugin<Project> {

  public static final String TASK_GROUP = "Dagger"

  private static final Logger LOG = Logging.getLogger(DaggerPlugin.class)

  @Override
  public void apply(Project project) {

    if (project.plugins.hasPlugin(DaggerPlugin.class)) {
      return;
    }

    LOG.info("Applying Dagger plugin")

    if (!project.plugins.hasPlugin(JavaPlugin.class)) {
      project.plugins.apply(JavaPlugin.class)
    }

    if (project.plugins.hasPlugin(WarPlugin.class)) {
      project.configurations {
        dagger.extendsFrom compile, providedRuntime, providedCompile
      }
    } else {
      project.configurations {
        dagger.extendsFrom compile
      }
    }

    project.extensions.create(DaggerPluginExtension.NAME, DaggerPluginExtension)

    project.task(type: CleanDaggerSourcesDir, "cleanDaggerSourcesDir")
    project.task(type: InitDaggerSourcesDir, "initDaggerSourcesDir")
    project.task(type: DaggerCompile, "compileDagger")

    project.tasks.clean.dependsOn project.tasks.cleanDaggerSourcesDir
    project.tasks.compileDagger.dependsOn project.tasks.initDaggerSourcesDir
    project.tasks.compileJava.dependsOn project.tasks.compileDagger

    project.afterEvaluate {

      project.dependencies {
        dagger project.extensions.dagger.processorLibrary
        compile project.extensions.dagger.library
      }

      project.sourceSets {
        dagger {
          java.srcDirs = [project.extensions.dagger.daggerSourcesDir]
        }
      }

      project.compileJava {
        source project.extensions.dagger.daggerSourcesDir
      }
    }
  }
}