package com.ewerk.gradle.plugins

import com.ewerk.gradle.plugins.tasks.CleanJaxb2SourcesDir
import com.ewerk.gradle.plugins.tasks.GenerateJaxb2Classes
import com.ewerk.gradle.plugins.tasks.InitJaxb2SourcesDir
import org.gradle.api.Project
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
class Jaxb2PluginTest {
  private Project project

  @BeforeMethod
  void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(Jaxb2Plugin.class)
  }

  @Test
  void testPluginAppliesItself() {
    assertThat(project.plugins.hasPlugin(Jaxb2Plugin.class), is(true))
  }

  @Test
  void testReApplyDoesNotFail() {
    project.plugins.apply(Jaxb2Plugin.class)
  }

  @Test
  void testPluginAppliesJavaPlugin() {
    assertThat(project.plugins.hasPlugin(JavaPlugin.class), is(true))
  }

  @Test
  void testPluginRegistersJaxb2Extensions() {
    assertThat(project.extensions.jaxb2, notNullValue())
  }

  @Test
  void testInitTaskIsAvailable() {
    assertThat(project.tasks.initJaxb2SourcesDir, notNullValue())
  }

  @Test
  void testCleanTaskIsAvailable() {
    assertThat(project.tasks.cleanJaxb2SourcesDir, notNullValue())
  }

  @Test
  void testGenerateTaskIsAvailable() {
    assertThat(project.tasks.generateJaxb2Classes, notNullValue())
  }

  @Test
  void testInitTaskType() {
    assertThat(project.tasks.initJaxb2SourcesDir, instanceOf(InitJaxb2SourcesDir.class))
  }

  @Test
  void testCleanTaskType() {
    assertThat(project.tasks.cleanJaxb2SourcesDir, instanceOf(CleanJaxb2SourcesDir.class))
  }

  @Test
  void testGenerateTaskType() {
    assertThat(project.tasks.generateJaxb2Classes, instanceOf(GenerateJaxb2Classes.class))
  }
}
