package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.ArtifactoryDebPublishPlugin
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Gradle task the publishes the desired .deb archive to the configured Artifactory repo.
 * It relies on OK HTTP for transport, the Artifactory HTTP API is used for publication.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class PushDebToArtifactory extends DefaultTask {
  static final String DESCRIPTION = "Publishes the .deb archive to Artifactory."

  private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8")
  private final OkHttpClient client;

  PushDebToArtifactory() {
    this.group = ArtifactoryDebPublishPlugin.TASK_GROUP
    this.description = DESCRIPTION

    client = new OkHttpClient();
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  def publishToArtifactory() {
    println "---------------> "
  }
}
