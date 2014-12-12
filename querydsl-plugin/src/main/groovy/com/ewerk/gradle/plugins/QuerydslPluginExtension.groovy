package com.ewerk.gradle.plugins

/**
 * DLS extension for the Querydsl plugin. Provides some convenient configuration options.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class QuerydslPluginExtension {

  static final String NAME = "queryDsl"

  static final String DEFAULT_QUERYDSL_SOURCES_DIR = new File("src/querydsl/java")
  static final String DEFAULT_LIBRARY = "com.mysema.querydsl:querydsl-apt:3.6.0"

  String querydslSourcesDir = DEFAULT_QUERYDSL_SOURCES_DIR
  String library = DEFAULT_LIBRARY
}
