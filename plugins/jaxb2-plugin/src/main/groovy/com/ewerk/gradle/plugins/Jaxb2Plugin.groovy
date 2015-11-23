package com.ewerk.gradle.plugins

import com.ewerk.gradle.plugins.tasks.CleanJaxb2SourcesDir
import com.ewerk.gradle.plugins.tasks.GenerateJaxb2Classes
import com.ewerk.gradle.plugins.tasks.InitJaxb2SourcesDir
import com.ewerk.gradle.plugins.tasks.XjcTaskConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.internal.reflect.Instantiator
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class Jaxb2Plugin implements Plugin<Project> {
  public static final String TASK_GROUP = "Jaxb2"

  private static final Logger LOG = LoggerFactory.getLogger(Jaxb2Plugin.class)

  @Override
  void apply(final Project project) {
    // do nothing if plugin is already applied
    if (project.plugins.hasPlugin(Jaxb2Plugin.class)) {
      return;
    }

    LOG.info("Applying JAXB2 plugin")

    // apply core 'java' plugin if not present to make 'sourceSets' available
    if (!project.plugins.hasPlugin(JavaPlugin.class)) {
      project.plugins.apply(JavaPlugin.class)
    }

    // add 'autoValue' DSL extension
    project.extensions.create(Jaxb2PluginExtension.NAME, Jaxb2PluginExtension)
    project.extensions.jaxb2.extensions.xjc = project.container(XjcTaskConfig) { String name ->
      return project.gradle.services.get(Instantiator).newInstance(XjcTaskConfig, name)
    }

    project.configurations.create('jaxb2')

    // add new tasks for creating/cleaning the auto-value sources dir
    project.task(type: CleanJaxb2SourcesDir, "cleanJaxb2SourcesDir")
    project.task(type: InitJaxb2SourcesDir, "initJaxb2SourcesDir")
    project.task(type: GenerateJaxb2Classes, "generateJaxb2Classes")

    // make 'compileJava' require the new task, so that all sources are available
    project.tasks.clean.dependsOn project.tasks.cleanJaxb2SourcesDir
    project.tasks.generateJaxb2Classes.dependsOn project.tasks.initJaxb2SourcesDir
    project.tasks.compileJava.dependsOn project.tasks.generateJaxb2Classes

    project.dependencies {
      jaxb2 'org.slf4j:slf4j-simple:1.7.13'
    }

    // make sure project configuration has finished
    project.afterEvaluate {
      addSourceSet(project)
    }
  }

  private void addSourceSet(Project project) {
    Set<XjcTaskConfig> xjcConfigs = project.extensions.jaxb2.xjc

    for (XjcTaskConfig theConfig : xjcConfigs) {
      File sourcesDir = project.file(theConfig.generatedSourcesDir)
      LOG.info("Create source set 'jaxb2': {}", sourcesDir.absolutePath)
      project.sourceSets {
        jaxb2 {
          java.srcDirs += [sourcesDir]
        }
      }
    }
  }
}
