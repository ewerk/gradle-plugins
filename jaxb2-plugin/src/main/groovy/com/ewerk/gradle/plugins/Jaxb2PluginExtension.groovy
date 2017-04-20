package com.ewerk.gradle.plugins

/**
 * DSL extension for the Jaxb2 plugin. Provides some convenient configuration options.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class Jaxb2PluginExtension {
  static final String NAME = 'jaxb2'

  String taskName = 'org.jvnet.jaxb2_commons.xjc.XJC2Task'
}



