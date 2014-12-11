package com.ewerk.gradle.plugins

import com.ewerk.gradle.plugins.tasks.CleanAutoValueSourcesDir
import com.ewerk.gradle.plugins.tasks.InitAutoValueSourcesDir
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.JavaPlugin

/**
 * This plugin can be used to easily create Google Auto-Value classes and attach them to the project
 * classpath.<br/><br/>
 *
 * The plugin registers the extension 'autoValue' so that plugin specific configuration can
 * be overwritten within the build sScript. Please see the readme doc on Github for details on that.
 * <br/><br/>
 *
 * The plugin will generate an additional source directory into where the auto-value
 * classes will be compiled, so that they can be ignored from SCM commits. Per default, this will
 * be {@link AutoValuePluginExtension#DEFAULT_AUTO_VALUE_SOURCES_DIR}.
 * <br/><br/>
 *
 * @see AutoValuePluginExtension
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class AutoValuePlugin implements Plugin<Project> {

  private static final Logger LOG = Logging.getLogger(AutoValuePlugin.class)

  @Override
  void apply(final Project project) {

    // do nothing if plugin is already applied
    if (project.plugins.hasPlugin(AutoValuePlugin.class)) {
      return;
    }

    // apply core 'java' plugin if not present to make 'sourceSets' available
    if (!project.plugins.hasPlugin(JavaPlugin.class)) {
      project.plugins.apply(JavaPlugin.class)
    }

    // add 'autoValue' DSL extension
    project.extensions.create(AutoValuePluginExtension.NAME, AutoValuePluginExtension)

    // add new tasks for creating/cleaning the auto-value sources dir
    project.task(type: CleanAutoValueSourcesDir, "cleanAutoValueSourcesDir")
    project.task(type: InitAutoValueSourcesDir, "initAutoValueSourcesDir")

    // make 'compileJava' require the new task, so that all sources are available
    project.tasks.compileJava.dependsOn project.tasks.initAutoValueSourcesDir
    project.tasks.clean.dependsOn project.tasks.cleanAutoValueSourcesDir

    // make sure project configuration has finished
    project.afterEvaluate {
      File autoValueSourcesDir = autoValueSourcesDir(project)

      addLibrary(project)
      addSourceSet(project, autoValueSourcesDir)
      addCompilerOption(project, autoValueSourcesDir)
    }
  }

  private void addCompilerOption(Project project, sourcesDir) {
    project.tasks.compileJava {
      options.compilerArgs = options.compilerArgs + ["-s", sourcesDir.absolutePath]
    }
  }

  private void addLibrary(Project project) {
    def library = project.extensions.autoValue.library
    LOG.info("Auto-Value library: {}", library)
    project.dependencies {
      compile library
    }
  }

  private void addSourceSet(Project project, File sourcesDir) {
    LOG.info("Create source set 'auto-value'.");

    project.sourceSets {
      autoValue {
        java.srcDirs = [sourcesDir]
      }
    }
  }

  private static File autoValueSourcesDir(Project project) {
    String path = project.extensions.autoValue.autoValueSourcesDir
    File autoValueSourcesDir = project.file(path)
    LOG.info("Auto-value sources dir: {}", autoValueSourcesDir.absolutePath);
    return autoValueSourcesDir
  }
}
