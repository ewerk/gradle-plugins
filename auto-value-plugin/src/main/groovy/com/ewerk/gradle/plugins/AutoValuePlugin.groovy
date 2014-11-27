package com.ewerk.gradle.plugins

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

  private static final String GROUP = "Code generation"
  private static final String DESCRIPTION = "Creates the Auto-value sources dir."

  private static final Logger LOG = Logging.getLogger(AutoValuePlugin.class)

  @Override
  void apply(final Project project) {

    if (project.plugins.hasPlugin(AutoValuePlugin.class)) {
      return;
    }

    if (!project.plugins.hasPlugin(JavaPlugin.class)) {
      project.plugins.apply(JavaPlugin.class)
    }

    project.extensions.create("autoValue", AutoValuePluginExtension.class)

    File generatedSourcesDir = project.autoValue.generatedSourcesDir
    LOG.debug("Auto-value sources dir: {}", generatedSourcesDir.absolutePath);

    project.sourceSets {
      generated {
        java.srcDir project.file(generatedSourcesDir)
      }
    }

    project.task(group: GROUP, description: DESCRIPTION, "initAutoValueSourcesDir") {
      LOG.debug("Creating auto-value sources dir");

      // TODO h.stolzenberg: impl task
    }

    project.tasks.compileJava.dependsOn project.tasks.initAutoValueSourcesDir
  }
}
