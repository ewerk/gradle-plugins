package com.ewerk.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.SourceSet
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.*
import static org.hamcrest.MatcherAssert.assertThat

/**
 * @author holgerstolzenberg
 * @since 1.0.6
 */
class IntegrationTestPluginTest {
  private Project project

  @BeforeMethod
  public void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(IntegrationTestPlugin.class)
  }

  @Test
  public void testPluginAppliesJavaPlugin() {
    assertThat(project.plugins.hasPlugin(JavaPlugin.class), is(true))
  }

  @Test
  public void testPluginAppliesItself() {
    assertThat(project.plugins.hasPlugin(IntegrationTestPlugin.class), is(true))
  }

  @Test
  public void testIntegrationTestTaskAvailable() {
    assertThat(project.tasks.integrationTest, notNullValue())
  }

  @Test
  public void testIntegrationTestTaskType() {
    final Task integrationTest = project.tasks.integrationTest
    assertThat(integrationTest, instanceOf(org.gradle.api.tasks.testing.Test.class))
  }

  @Test
  public void testAllTestsTaskAvailable() {
    assertThat(project.tasks.allTests, notNullValue())
  }

  @Test
  public void testAllTestsTaskGroup() {
    assertThat(project.tasks.allTests.group, equalTo("Integration test"))
  }

  @Test
  public void testAllTestsTaskType() {
    final Task integrationTest = project.tasks.allTests
    assertThat(integrationTest, instanceOf(Task.class))
  }

  @Test
  public void testIntegrationSourceSetPresent() {
    SourceSet integration = project.sourceSets.integration
    assertThat(integration, notNullValue());
  }

  @Test
  public void testIntegrationJavaDirPresent() {
    SourceSet integration = project.sourceSets.integration
    SourceDirectorySet java = integration.java
    assertThat(java, notNullValue());
  }

  @Test
  public void testIntegrationResourceDirPresent() {
    SourceSet integration = project.sourceSets.integration
    SourceDirectorySet resources = integration.resources
    assertThat(resources, notNullValue());
  }
}
