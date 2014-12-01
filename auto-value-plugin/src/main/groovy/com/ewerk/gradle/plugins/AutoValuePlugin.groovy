package com.ewerk.gradle.plugins

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
 * be overwritten we buildScript. Please see the readme doc on Github for details on that.
 * <br/><br/>
 *
 * The plugin will generate an additional source directory into where the auto-value
 * classes will be compiled, so that they can be ignored from SCM commits. Per default, this will
 * be {@link AutoValuePluginExtension#DEFAULT_GENERATED_SOURCES_DIR}.
 * <br/><br/>
 *
 * TODO h.stolzenberg: complete documentation
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

    // add new task for creating the generated sources dir
    project.task(type: InitAutoValueSourcesDir, "initAutoValueSourcesDir")

    // make 'compileJava' require the new task, so that all sources are available
    project.tasks.compileJava.dependsOn project.tasks.initAutoValueSourcesDir

    // make sure project configuration has finished
    project.afterEvaluate {
      File generatedSourcesDir = generatedSourcesDir(project)

      addAutoValueLibrary(project)
      addSourceSet(project, generatedSourcesDir)
      addCompilerOption(project, generatedSourcesDir)
    }
  }

  private void addCompilerOption(Project project, generatedSourcesDir) {
    project.tasks.compileJava {
      options.compilerArgs = ["-s", generatedSourcesDir.absolutePath]
    }
  }

  private void addAutoValueLibrary(Project project) {
    def library = project.extensions.autoValue.library
    LOG.info("Auto-Value library: {}", library)
    project.dependencies {
      compile library
    }
  }

  private void addSourceSet(Project project, File generatedSourcesDir) {
    project.sourceSets {
      generated {
        java.srcDirs = [project.file(generatedSourcesDir)]
      }
    }
  }

  private static File generatedSourcesDir(Project project) {
    File generatedSourcesDir = project.extensions.autoValue.generatedSourcesDir
    LOG.info("Auto-value sources dir: {}", generatedSourcesDir.absolutePath);
    return generatedSourcesDir
  }
}
