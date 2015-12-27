package com.ewerk.gradle.plugins

import com.ewerk.gradle.plugins.tasks.CleanDaggerSourcesDir
import com.ewerk.gradle.plugins.tasks.DaggerCompile
import com.ewerk.gradle.plugins.tasks.InitDaggerSourcesDir
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.WarPlugin
import org.gradle.api.tasks.Delete

/**
 * @author griffio
 *
 * Dagger2 (http://google.github.io/dagger/) is a library with a processor dependency (com.google.dagger:dagger-compiler)
 * and compile transitive dependency (com.google.dagger:dagger) that is required for the generated code.
 *
 * Apply configuration to JavaPlugin project.
 *   A separate DaggerCompile task is executed with the annotation processor
 *
 * Apply configuration to Android project.
 *   The source is ${project.buildDir}/generated/source/dagger/<variant>
 *   The variant.javaCompile compile task is executed with the annotation processor
 */
public class DaggerPlugin implements Plugin<Project> {

  public static final String TASK_GROUP = "Dagger"

  private static final Logger LOG = Logging.getLogger(DaggerPlugin.class)

  @Override
  public void apply(Project project) {

    if (project.plugins.hasPlugin(DaggerPlugin.class)) {
      return;
    }

    project.extensions.create(DaggerPluginExtension.NAME, DaggerPluginExtension)

    if (hasAndroidProject(project)) {
      androidApply(project)
    } else {
      javaApply(project)
    }
  }

  def javaApply(project) {

    LOG.info("Apply Java Dagger plugin")

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

  def androidApply(project) {

    LOG.info("Apply Android Dagger plugin")

    project.configurations {
      dagger.extendsFrom compile
    }

    // needed for Android Studio to show dependencies
    project.dependencies {
      compile project.dagger.library
      dagger project.dagger.processorLibrary
    }

    project.afterEvaluate {

      def generatedOutputDir = new File(project.buildDir as File, "generated/source/dagger")

      project.tasks.create(name: 'cleanDaggerSourcesDir', type: Delete) {
        delete generatedOutputDir
      }

      project.tasks.clean.dependsOn project.tasks.cleanDaggerSourcesDir

      def android = project.extensions.android

      def variants = projectAndroidVariants(project)

      android.sourceSets.main.java.srcDirs += [generatedOutputDir]

      variants.all { variant ->

        def variantOutputDir = new File(generatedOutputDir as File, variant.dirName as String)

        variant.javaCompile.classpath += project.configurations.dagger

        variant.javaCompile.doFirst() {
          variantOutputDir.mkdirs()
        }

        variant.javaCompile.options.compilerArgs += [
            "-s", variantOutputDir.absolutePath,
            "-processor", project.dagger.PROCESSOR
        ]

        //Makes the generated/source folder visible in Android Studio
        variant.addJavaSourceFoldersToModel(variantOutputDir)
      }
    }
  }

  static def hasAndroidProject(Project project) {
    return project.plugins.hasPlugin('com.android.application') || project.plugins.hasPlugin('com.android.library')
  }

  static def projectAndroidVariants(Project project) {
    if (project.android.hasProperty('applicationVariants')) {
      return project.android.applicationVariants
    } else if (project.android.hasProperty('libraryVariants')) {
      return project.android.libraryVariants
    } else {
      throw new GradleException('Android project must contain application variants or library variants')
    }
  }

  static def verifyNotWithinMainBuildSrc(Project project) {
    def daggerSourcesDir = project.file(project.dagger.daggerSourcesDir) as File
    project.sourceSets.main.java.srcDirs.each { d ->
      if (d.absolutePath.equals(daggerSourcesDir.absolutePath)) {
        throw new GradleException("The configured daggerSourcesDir must specify a separate location to existing source code.")
      }
    }
    return daggerSourcesDir
  }
}