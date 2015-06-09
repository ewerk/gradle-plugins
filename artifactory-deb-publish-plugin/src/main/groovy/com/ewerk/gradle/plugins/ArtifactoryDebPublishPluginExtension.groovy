package com.ewerk.gradle.plugins

import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

/**
 * DLS extension for the auto-value plugin. Provides some convenient configuration options.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class ArtifactoryDebPublishPluginExtension {

  private static final Logger LOG = Logging.getLogger(ArtifactoryDebPublishPluginExtension.class)

  static final String NAME = "artifactoryDebPublish"

  // Artifactory repository url
  String baseUrl;

  // Artifactory account credentials
  String user;
  String password;

  // the repository key to push to
  String repoKey;

  // debian package meta information
  String distribution;
  String component;
  String arch;

  // the deb archive to publish
  File archive;

  public String baseUrl() {
    if (baseUrl == null || baseUrl.isEmpty()) {
      throw new RuntimeException("The 'baseUrl' property was not configured.")
    }
    LOG.info("Base URL: {}", baseUrl)
    baseUrl
  }

  public File archiveFile() {
    if (archive == null) {
      throw new RuntimeException("Archive file to publish was not configured.");
    }
    if (!archive.exists()) {
      throw new RuntimeException("The file '$archive' does not exist.");
    }

    archive
  }

  public String password() {
    LOG.info("Password: {}", password != null && !password.isEmpty() ? "***" : "")
    password
  }

  public String user() {
    LOG.info("User: {}", user)
    user
  }

  public String repoKey() {
    if (repoKey == null || repoKey.isEmpty()) {
      throw new RuntimeException("The 'repoKey' property was not configured.")
    }
    LOG.info("RepoKey: {}", repoKey)
    repoKey
  }

  public String component() {
    if (component == null || component.isEmpty()) {
      throw new RuntimeException("The 'component' property was not configured.")
    }
    LOG.info("Component: {}", component)
    component
  }

  public String arch() {
    if (arch == null || arch.isEmpty()) {
      throw new RuntimeException("The 'arch' property was not configured.")
    }
    LOG.info("Architecture: {}", arch)
    arch
  }

  public String distribution() {
    if (distribution == null || distribution.isEmpty()) {
      throw new RuntimeException("The 'distribution' property was not configured.")
    }
    LOG.info("Distribution: {}", distribution)
    distribution
  }
}
