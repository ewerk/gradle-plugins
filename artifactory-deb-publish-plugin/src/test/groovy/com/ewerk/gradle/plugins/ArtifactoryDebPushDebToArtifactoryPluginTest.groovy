package com.ewerk.gradle.plugins

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.is
import static org.hamcrest.CoreMatchers.notNullValue
import static org.hamcrest.MatcherAssert.assertThat

/**
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class ArtifactoryDebPushDebToArtifactoryPluginTest {
  private Project project

  @BeforeMethod
  public void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(ArtifactoryDebPublishPlugin.class)

    // TODO holgerstolzenberg: config
    project.extensions.artifactoryDebPublish.url = "http://debian.any.host.com";
  }

  @Test
  public void testPluginAppliesItself() {
    assertThat(project.plugins.hasPlugin(ArtifactoryDebPublishPlugin.class), is(true))
  }

  @Test
  public void testReApplyDoesNotFail() {
    project.plugins.apply(ArtifactoryDebPublishPlugin.class)
  }

  @Test
  public void testPluginRegistersExtensions() {
    assertThat(project.extensions.artifactoryDebPublish, notNullValue())
  }

  @Test
  public void testPluginTasksAreAvailable() {
    assertThat(project.tasks.pushDebToArtifactory, notNullValue())
  }

  @Test
  public void testAfterEvaluate() {
    project.evaluate()
  }
}
