### Artifactory .deb publish plugin

#### Description

Currently publishing .deb archives to Artifactory via Gradle is not very straight forward. The 
publish plugin provided by JFrog only documents examples for publishing Maven artifacts.
 
Deploying .deb archives to artifactory is possible through its HTTP API. This plugin will allow
for publishing .deb archives with the relevant meta data to Artifactory, using the aforementioned
HTTP API.

The plugin relies on the [OkHttp](change_log.md) library for pushing the .deb archive to Artifactory
via HTTP PUT.

Please have a look at the plugins [change log](http://square.github.io/okhttp/).

#### Configuration

##### baseUrl
The Artifactory servers base url

##### user (optional)
The user for authenticating at Artifactory if anonymous access is disabled

##### password (optional)
The password for authenticating at Artifactory if anonymous access is disabled

##### repoKey
The Artifactory repository ID to publish to

##### distribution
The Debian distribution to publish for (wheezy, jessie, ...)

##### distribution
The Debian component to publish for (main, contrib, non-free, ...)

##### arch
The Debian architecture to publish for (i386, amd64, ...)

#### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.artifactory-deb-publish" version "1.0.0"
}

// the following closure demonstrates some of the configuration defaults and is not necessary
artifactoryDebPublish {
  baseUrl = 'https://artifactory.it.ewerk.com'
  user = 'my.user'
  password = '***'
  repoKey = 'debian_snapshots'
  distribution = 'jessie'
  component = 'non-free'
  arch = 'amd64'
  archive = file('src/main/resources/helloworld_1.0_amd64.deb')
}
```