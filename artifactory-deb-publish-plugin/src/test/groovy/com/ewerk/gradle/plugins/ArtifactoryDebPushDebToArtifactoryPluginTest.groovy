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
  void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(ArtifactoryDebPublishPlugin.class)

    project.extensions.artifactoryDebPublish.baseUrl = "http://debian.any.host.com"
    project.extensions.artifactoryDebPublish.repoKey = "debian_repo"
    project.extensions.artifactoryDebPublish.distribution = "jessie"
    project.extensions.artifactoryDebPublish.component = "non-free"
    project.extensions.artifactoryDebPublish.arch = "amd64"
  }

  @Test
  void testPluginAppliesItself() {
    assertThat(project.plugins.hasPlugin(ArtifactoryDebPublishPlugin.class), is(true))
  }

  @Test
  void testReApplyDoesNotFail() {
    project.plugins.apply(ArtifactoryDebPublishPlugin.class)
  }

  @Test
  void testPluginRegistersExtensions() {
    assertThat(project.extensions.artifactoryDebPublish, notNullValue())
  }

  @Test
  void testPluginTasksAreAvailable() {
    assertThat(project.tasks.pushDebToArtifactory, notNullValue())
  }

  @Test
  void testAfterEvaluate() {
    project.evaluate()
  }
}
