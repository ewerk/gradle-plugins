package com.ewerk.gradle.plugins

/**
 * TODO holgerstolzenberg: Document
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class AutoValuePluginExtension {

  static final String NAME = "autoValue"

  static final File DEFAULT_GENERATED_SOURCES_DIR = new File("src/generated/java")
  static final String DEFAULT_LIBRARY = "com.google.auto.value:auto-value:1.0-rc2"
  static final boolean DEFAULT_DELETE = true

  File generatedSourcesDir = DEFAULT_GENERATED_SOURCES_DIR
  String library = DEFAULT_LIBRARY
  Boolean delete = DEFAULT_DELETE
}
