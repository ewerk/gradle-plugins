package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.ArtifactoryDebPublishPlugin
import com.ewerk.gradle.plugins.ArtifactoryDebPublishPluginExtension
import com.ewerk.gradle.plugins.util.ArtifactoryHttpClient
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
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

  private static final Logger LOG = Logging.getLogger(PushDebToArtifactory.class)

  PushDebToArtifactory() {
    this.group = ArtifactoryDebPublishPlugin.TASK_GROUP
    this.description = DESCRIPTION
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  def publishToArtifactory() {
    LOG.info("Pushing .deb to Artifactory")

    ArtifactoryHttpClient client = new ArtifactoryHttpClient(extension().baseUrl(),
        extension().user(),
        extension().password(),
        extension().repoKey(),
        extension().archiveFile().name,
        extension().component(),
        extension().distribution(),
        extension().arch());

    client.publish(extension().archiveFile())
  }

  private ArtifactoryDebPublishPluginExtension extension() {
    project.extensions.artifactoryDebPublish
  }
}