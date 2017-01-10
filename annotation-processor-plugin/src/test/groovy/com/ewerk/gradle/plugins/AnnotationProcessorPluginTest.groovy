package com.ewerk.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.plugins.WarPlugin
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.*
import static org.hamcrest.MatcherAssert.assertThat

class AnnotationProcessorPluginTest {

  private Project project

  @BeforeMethod
  void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(AnnotationProcessorPlugin.class)
    project.plugins.apply(WarPlugin.class)
    project.extensions.annotationProcessor.library = "com.querydsl:querydsl-apt:4.0.0"
    project.extensions.annotationProcessor.processor = "com.querydsl.apt.morphia.MorphiaAnnotationProcessor"
  }

  @Test
  void testBasePlugin() {
    assertThat(project.plugins.hasPlugin(BasePlugin.class), is(true))
  }

  @Test
  void testPluginAppliesItself() {
    assertThat(project.plugins.hasPlugin(AnnotationProcessorPlugin.class), is(true))
  }

  @Test
  void testReApplyDoesNotFail() {
    project.plugins.apply(AnnotationProcessorPlugin.class)
  }

  @Test
  void testPluginAppliesJavaPlugin() {
    assertThat(project.plugins.hasPlugin(AnnotationProcessorPlugin.class), is(true))
  }

  @Test
  void testPluginEvaluatesDependencies() {
    project.evaluate()
    def lib = project.configurations.annotationProcessor.dependencies.collect {
      "$it.group:$it.name:$it.version" as String
    }.toSet()
    assertThat(lib, hasItem(project.extensions.annotationProcessor.library))
  }

  @Test
  void testPluginEvaluatesCompileOptions() {
    project.evaluate()
    def args = project.tasks.compileAnnotationProcessor.options.compilerArgs as List
    assertThat(args, hasItems('-proc:only', '-s', '-processor', project.extensions.annotationProcessor.processor,
        new File(project.projectDir, AnnotationProcessorPluginExtension.DEFAULT_SOURCES_DIR).path))
  }

  @Test
  void testDefaultGeneratedSourcesDirIsSet() {
    assertThat(project.extensions.annotationProcessor.sourcesDir as String,
        equalTo(AnnotationProcessorPluginExtension.DEFAULT_SOURCES_DIR))
  }

  @Test
  void testDefaultLibraryIsSet() {
    assertThat(project.extensions.annotationProcessor.library as String,
        equalTo("com.querydsl:querydsl-apt:4.0.0"))
  }
}
