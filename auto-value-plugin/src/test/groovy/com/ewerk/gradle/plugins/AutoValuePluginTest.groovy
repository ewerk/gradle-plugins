package com.ewerk.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.JavaPlugin
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.*
import static org.hamcrest.MatcherAssert.assertThat

/**
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class AutoValuePluginTest {
  private Project project

  @BeforeMethod
  public void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(AutoValuePlugin.class)
  }

  @Test
  public void testPluginAppliesItself() {
    assertThat(project.plugins.hasPlugin(AutoValuePlugin.class), is(true))
  }

  @Test
  public void testReApplyDoesNotFail() {
    project.plugins.apply(AutoValuePlugin.class)
  }

  @Test
  public void testPluginAppliesJavaPlugin() {
    assertThat(project.plugins.hasPlugin(JavaPlugin.class), is(true))
  }

  @Test
  public void testPluginRegistersAutoValueExtensions() {
    assertThat(project.extensions.autoValue, notNullValue())
  }

  @Test
  public void testPluginTasksAreAvailable() {
    assertThat(project.tasks.initAutoValueSourcesDir, notNullValue())
  }

  @Test
  public void testTaskTypes() {
    final Task initTask = project.tasks.initAutoValueSourcesDir
    assertThat(initTask, instanceOf(Task.class))
  }
}
