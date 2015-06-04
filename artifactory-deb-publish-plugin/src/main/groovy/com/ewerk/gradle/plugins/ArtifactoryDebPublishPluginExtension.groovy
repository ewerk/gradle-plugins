package com.ewerk.gradle.plugins

/**
 * DLS extension for the auto-value plugin. Provides some convenient configuration options.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class ArtifactoryDebPublishPluginExtension {
  static final String NAME = "artifactoryDebPublish"

  // Artifactory repository url
  String url;

  // Artifactory account credentials
  String user;
  String pass;

  // the repository key to push to
  String repoKey;

  // debian package meta information
  String distribution;
  String component;
  String arch;

  // the deb archive to publish
  String file;
}
