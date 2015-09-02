package com.ewerk.gradle.plugins

/**
 * DLS extension for the auto-value plugin. Provides some convenient configuration options.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class AutoValuePluginExtension {

  static final String NAME = "autoValue"

  static final String DEFAULT_AUTO_VALUE_SOURCES_DIR = new File("src/auto-value/java")
  static final String DEFAULT_LIBRARY = "com.google.auto.value:auto-value:1.0"

  String autoValueSourcesDir = DEFAULT_AUTO_VALUE_SOURCES_DIR
  String library = DEFAULT_LIBRARY
}
