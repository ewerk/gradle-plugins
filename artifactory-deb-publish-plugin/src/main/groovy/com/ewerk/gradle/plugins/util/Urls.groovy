package com.ewerk.gradle.plugins.util

/**
 * Utility class that helps constructing the Artifactory publication URLs.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class Urls {
  public static GString construct(String url, String repoKey, String name, String component,
      String prefix, String distribution, String arch) {
    "$url/$repoKey/pool/$component/$prefix/$name;deb.distribution=$distribution;deb.component=$component;deb.architecture=$arch"
  }

  public static String prefix(String name) {
    name.substring(0, 1)
  }
}
