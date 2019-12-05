package com.ewerk.gradle.plugins.tasks

/**
 * Simple extension bean. Is used within the plugins extension a wrapper for the 'xjc' container.
 * This extension is declared in the plugins main extension itself, it is created on-the-fly
 * on plugin apply. See the plugin class for that.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class XjcTaskConfig {
  String name
  String generatedSourcesDir = 'src/generated/java'
  String basePackage
  String schema
  String catalog
  String bindingsDir
  String includedBindingFiles
  String encoding = 'UTF-8'
  String additionalArgs = ''
  boolean createSourceSet = true
  boolean extension = false

  XjcTaskConfig(String name) {
    this.name = name
  }
}
