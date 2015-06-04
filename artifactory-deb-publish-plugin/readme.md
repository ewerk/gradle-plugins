### Artifactory .deb publish plugin

#### Description

Currently publishing .deb archives to Artifactory via Gradle is not very straight forward. The 
publish plugin provided by JFrog only documents examples for publishing Maven artifacts.
 
Deploying .deb archives to artifactory is possible through its HTTP API. This plugin will allow
for publishing .deb archives with the relevant meta data to Artifactory, using the aforementioned
HTTP API.

Please have a look at the plugins [change log](change_log.md).

#### Configuration

TODO holgerstolzenberg: document

#### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.artifactory-deb-publish" version "1.0.0"
}

// the following closure demonstrates some of the configuration defaults and is not necessary
artifactoryDebPublish {
  TODO holgerstolzenberg: document
}
```