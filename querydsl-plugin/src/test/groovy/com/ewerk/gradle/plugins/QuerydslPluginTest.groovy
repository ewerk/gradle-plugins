package com.ewerk.gradle.plugins

import com.ewerk.gradle.plugins.tasks.InitQuerydslSourcesDir
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.WarPlugin
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.*
import static org.hamcrest.MatcherAssert.assertThat

/**
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class QuerydslPluginTest {
  private Project project

  @BeforeMethod
  void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(QuerydslPlugin.class)
    project.plugins.apply(WarPlugin.class)

    project.extensions.querydsl.jpa = true
    project.extensions.querydsl.jdo = true
    project.extensions.querydsl.roo = true
    project.extensions.querydsl.hibernate = true
    project.extensions.querydsl.morphia = true
    project.extensions.querydsl.springDataMongo = true
    project.extensions.querydsl.querydslDefault = true
  }

  @Test
  void testPluginAppliesItself() {
    assertThat(project.plugins.hasPlugin(QuerydslPlugin.class), is(true))
  }

  @Test
  void testReApplyDoesNotFail() {
    project.plugins.apply(QuerydslPlugin.class)
  }

  @Test
  void testPluginAppliesJavaPlugin() {
    assertThat(project.plugins.hasPlugin(JavaPlugin.class), is(true))
  }

  @Test
  void testPluginRegistersQuerydslExtensions() {
    assertThat(project.extensions.querydsl, notNullValue())
  }

  @Test
  void testPluginProcessorsFromQuerydslExtensions() {
    assertThat(project.extensions.querydsl.processors(), notNullValue())
  }

  @Test
  void testPluginTasksAreAvailable() {
    assertThat(project.tasks.initQuerydslSourcesDir, notNullValue())
    assertThat(project.tasks.cleanQuerydslSourcesDir, notNullValue())
  }

  @Test
  void testTaskTypes() {
    final Task initTask = project.tasks.initQuerydslSourcesDir
    assertThat(initTask, instanceOf(InitQuerydslSourcesDir.class))
  }

  @Test
  void testAfterEvaluate() {
    project.evaluate()

    DefaultExternalModuleDependency lib = project.configurations.compile.dependencies[0] as DefaultExternalModuleDependency

    String id = lib.group + ":" + lib.name + ":" + lib.version

    assertThat(id, equalTo(QuerydslPluginExtension.DEFAULT_LIBRARY))
    assertThat(project.tasks.compileQuerydsl, notNullValue())
  }

  @Test
  void testSourcesDirAfterEvaluate() {
    project.extensions.querydsl.querydslSourcesDir = "/java/other/src"
    project.evaluate()
    File sourcesDir = project.file(project.querydsl.querydslSourcesDir) as File
    assertThat(sourcesDir, equalTo(project.tasks.compileQuerydsl.destinationDir as File))
  }
}
