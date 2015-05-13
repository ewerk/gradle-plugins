package com.ewerk.gradle.plugins

import org.gradle.api.Plugin;
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.plugins.WarPlugin
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.compile.JavaCompile;

/**
 * Dagger2 compiler plugin (http://google.github.io/dagger/) is a library with a processor dependency and a
 * compile transitive dependency for the generated code.
 *
 * The processor dependency is added to the main JavaCompile task and is executed by classpath discovery.
 *
 * @author griffio
 */
public class DaggerPlugin implements Plugin<Project> {

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

    project.plugins.withType(JavaPlugin) {

      def javaConvention = project.convention.plugins.get("java") as JavaPluginConvention

      javaConvention.sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME) { SourceSet sourceSet ->
        LOG.info("task:" + sourceSet.getCompileJavaTaskName())
        def compileTask = project.tasks.withType(JavaCompile).getByName(sourceSet.getCompileJavaTaskName())
        compileTask.classpath += project.configurations.dagger
        compileTask.options.compilerArgs += [
            "-s", project.file(project.extensions.dagger.daggerSourcesDir).absolutePath
        ]
      }

      javaConvention.sourceSets.getByName(SourceSet.TEST_SOURCE_SET_NAME) { SourceSet sourceSet ->
        LOG.info("task:" + sourceSet.getCompileJavaTaskName())
      }

    }

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

    }

  }

}