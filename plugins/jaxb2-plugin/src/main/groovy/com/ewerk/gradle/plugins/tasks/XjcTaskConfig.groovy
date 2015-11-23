package com.ewerk.gradle.plugins.tasks

/**
 * TODO holgerstolzenberg: Document
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
public class XjcTaskConfig {
  String name
  String generatedSourcesDir = 'src/generated/java'
  String basePackage
  String schema

  public XjcTaskConfig(String name) {
    this.name = name
  }
}
