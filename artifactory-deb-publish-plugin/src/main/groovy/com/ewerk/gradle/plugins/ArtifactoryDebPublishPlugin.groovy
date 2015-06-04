package com.ewerk.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

/**
 * TODO holgerstolzenberg: document
 *
 * @see ArtifactoryDebPublishPluginExtension
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class ArtifactoryDebPublishPlugin implements Plugin<Project> {

  public static final String TASK_GROUP = "Publishing tasks"

  private static final Logger LOG = Logging.getLogger(ArtifactoryDebPublishPlugin.class)

  @Override
  void apply(final Project project) {

    // do nothing if plugin is already applied
    if (project.plugins.hasPlugin(ArtifactoryDebPublishPlugin.class)) {
      return;
    }

    LOG.info("Applying artifactory-deb-publish plugin")

    // add DSL extension
    project.extensions.create(ArtifactoryDebPublishPluginExtension.NAME,
        ArtifactoryDebPublishPluginExtension)
  }
}
