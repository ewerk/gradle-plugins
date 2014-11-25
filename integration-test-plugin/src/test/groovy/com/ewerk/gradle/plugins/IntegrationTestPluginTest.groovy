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
 * @author h.stolzenberg
 * @since 1.0.6
 */
class IntegrationTestPluginTest {
  private Project project
  private IntegrationTestPlugin plugin

  @BeforeMethod
  public void setup() {
    project = ProjectBuilder.builder().build()
    plugin = new IntegrationTestPlugin()
  }

  @Test
  public void testPluginAppliesJavaPlugin() {
    plugin.apply(project)
    assertThat(project.plugins.hasPlugin(JavaPlugin.class), is(true))
  }

  @Test
  public void testPluginAppliesItself() {
    project.plugins.apply(IntegrationTestPlugin.class)
    assertThat(project.plugins.hasPlugin(IntegrationTestPlugin.class), is(true))
  }

  @Test
  public void testIntegrationTestTaskAvailable() {
    plugin.apply(project)
    assertThat(project.tasks.integrationTest, notNullValue())
  }

  @Test
  public void testIntegrationTestTaskType() {
    plugin.apply(project)

    final Task integrationTest = project.tasks.integrationTest
    assertThat(integrationTest, instanceOf(org.gradle.api.tasks.testing.Test.class))
  }

  @Test
  public void testAllTestsTaskAvailable() {
    plugin.apply(project)
    assertThat(project.tasks.allTests, notNullValue())
  }

  @Test
  public void testAllTestsTaskGroup() {
    plugin.apply(project)
    assertThat(project.tasks.allTests.group, equalTo("Verification"))
  }

  @Test
  public void testAllTestsTaskType() {
    plugin.apply(project)
    final Task integrationTest = project.tasks.allTests
    assertThat(integrationTest, instanceOf(Task.class))
  }

  @Test
  public void testIntegrationSourceSetPresent() {
    plugin.apply(project)
    SourceSet integration = project.sourceSets.integration
    assertThat(integration, notNullValue());
  }

  @Test
  public void testIntegrationJavaDirPresent() {
    plugin.apply(project)
    SourceSet integration = project.sourceSets.integration
    SourceDirectorySet java = integration.java
    assertThat(java, notNullValue());
  }

  @Test
  public void testIntegrationResourceDirPresent() {
    plugin.apply(project)

    SourceSet integration = project.sourceSets.integration
    SourceDirectorySet resources = integration.resources
    assertThat(resources, notNullValue());
  }
}
