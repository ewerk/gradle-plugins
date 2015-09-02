package com.ewerk.gradle.plugins

/**
 * @author griffio
 */
class AnnotationProcessorPluginExtension {

  static final String NAME = "annotationProcessor"
  static final String DEFAULT_SOURCES_DIR = "src/generated/java"

  String sourcesDir = DEFAULT_SOURCES_DIR
  String library = ""
  String processor = ""
}
