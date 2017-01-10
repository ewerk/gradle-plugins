package com.ewerk.gradle.plugins

import com.ewerk.gradle.plugins.tasks.AnnotationProcessorCompile
import com.ewerk.gradle.plugins.tasks.CleanAnnotationProcessorGeneratedDir
import com.ewerk.gradle.plugins.tasks.InitAnnotationProcessorGeneratedDir
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.WarPlugin

/**
 * This plugin provides a simple integration point for an Annotation Processor capable of generating Java source.
 * A separate task [compileAnnotationProcessor] runs the annotation processor during build phase.
 *
 * A value for processor and library must be supplied to the extenstion task from the buildscript configuration.
 *
 * @author griffio
 */
class AnnotationProcessorPlugin implements Plugin<Project> {

  public static final String TASK_GROUP = "Annotation-Processor"

  private static final Logger LOG = Logging.getLogger(AnnotationProcessorPlugin.class)

  @Override
  void apply(Project project) {

    if (project.plugins.hasPlugin(AnnotationProcessorPlugin.class)) {
      return
    }

    LOG.info("Applying Annotation Processor plugin")

    if (!project.plugins.hasPlugin(JavaPlugin.class)) {
      project.plugins.apply(JavaPlugin.class)
    }

    if (project.plugins.hasPlugin(WarPlugin.class)) {
      project.configurations {
        annotationProcessor.extendsFrom compile, providedRuntime, providedCompile
      }
    } else {
      project.configurations {
        annotationProcessor.extendsFrom compile
      }
    }

    project.extensions.create(AnnotationProcessorPluginExtension.NAME,
        AnnotationProcessorPluginExtension)

    project.task(type: CleanAnnotationProcessorGeneratedDir, "cleanAnnotationProcessorSourcesDir")
    project.task(type: InitAnnotationProcessorGeneratedDir, "initAnnotationProcessorSourcesDir")
    project.task(type: AnnotationProcessorCompile, "compileAnnotationProcessor")

    project.tasks.clean.dependsOn project.tasks.cleanAnnotationProcessorSourcesDir
    project.tasks.compileAnnotationProcessor.dependsOn project.tasks.initAnnotationProcessorSourcesDir
    project.tasks.compileJava.dependsOn project.tasks.compileAnnotationProcessor

    project.afterEvaluate {

      if (project.extensions.annotationProcessor.processor == "") {
        throw new GradleException("processor - provide a fully qualified proc class.")
      }

      if (project.extensions.annotationProcessor.library == "") {
        throw new GradleException(
            "library - provide the dependency reference containing a processor.")
      }

      project.dependencies {
        annotationProcessor project.extensions.annotationProcessor.library
      }

      project.sourceSets {
        annotationProcessor {
          java.srcDirs = [project.extensions.annotationProcessor.sourcesDir]
        }
      }

      project.compileJava {
        source project.extensions.annotationProcessor.sourcesDir
      }
    }
  }
}