package com.ewerk.gradle.plugins;

/**
 * @author griffio
 */
public class DaggerPluginExtension {

  static final String NAME = "dagger"
  static final String DEFAULT_DAGGER_SOURCES_DIR = "src/dagger/java"
  static final String DEFAULT_PROCESSOR_LIBRARY = "com.google.dagger:dagger-compiler:2.0"
  static final String DEFAULT_LIBRARY = "com.google.dagger:dagger:2.0"

  String daggerSourcesDir = DEFAULT_DAGGER_SOURCES_DIR
  String library = DEFAULT_LIBRARY
  String processorLibrary = DEFAULT_PROCESSOR_LIBRARY

}