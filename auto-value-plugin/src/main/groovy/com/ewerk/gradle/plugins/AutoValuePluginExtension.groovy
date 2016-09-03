package com.ewerk.gradle.plugins

/**
 * DLS extension for the auto-value plugin. Provides some convenient configuration options.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class AutoValuePluginExtension {

  static final String NAME = "autoValue"

  static final String DEFAULT_AUTO_VALUE_SOURCES_DIR = "src/auto-value/java"

  String autoValueSourcesDir = DEFAULT_AUTO_VALUE_SOURCES_DIR
  String library = DEFAULT_LIBRARY
}
