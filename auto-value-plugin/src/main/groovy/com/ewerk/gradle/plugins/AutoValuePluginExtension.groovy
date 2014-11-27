package com.ewerk.gradle.plugins

/**
 * TODO holgerstolzenberg: Document
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class AutoValuePluginExtension {

  static final File DEFAULT_GENERATED_SOURCES_DIR = new File("src/generated/java")

  File generatedSourcesDir = DEFAULT_GENERATED_SOURCES_DIR
}
