[![License](http://img.shields.io/badge/license-Apache-brightgreen.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) [![Build Status](http://img.shields.io/travis/ewerk/gradle-plugins.svg?style=flat)](https://travis-ci.org/ewerk/gradle-plugins)

# EWERK Gradle Plugins
## Introduction
This repository was introduced in order to use the Github/Bintray/Gradle Plugin Portal
capabilities for publishing open source Gradle plugins and using them with the new plugin
declaration syntax introduced in Gradle 2.1.

The plugins are released using the [gradle-plugindev-plugin](https://github.com/etiennestuder/gradle-plugindev-plugin/blob/master/README.md).

The plugins can also be downloaded from [bintray](http://www.bintray.com) as long as they are not
available through the Gradle plugin portal.

## Plugins
### Integration test plugin

[ ![Download](https://api.bintray.com/packages/ewerk/gradle-plugins/integration-test-plugin/images/download.svg) ](https://bintray.com/holgerstolzenberg/gradle-plugins/integration-test-plugin/_latestVersion)

#### Description

The plugin can be used to execute integration tests from a sophisticated sourceSet. Just place
your integration tests and resources under `src/integration/java` and `src/integration/resources`
and run task `integrationTest`.

The plugin was inspired by the sources taken from [here](http://blog.lick-me.org/2014/07/fun-with-gradle-plugins-integration-tests/).

The plugin applies the task `integration-test` to the project. This tasks extends from the default
`test` task. Therefore all configuration properties from test task are available for integration 
tests also.

Please see [Gradle test config](http://www.gradle.org/docs/current/dsl/org.gradle.api.tasks.testing.Test.html) 
for details.

#### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "java"
  id "com.ewerk.gradle.plugins.integration-test" version "1.0.5"
}

integrationTest {
  useTestNG()

  minHeapSize = "128m"
  maxHeapSize = "512m"
}
```

__Use via JCenter__

```groovy
buildscript {
  repositories {
    jcenter()
  }

  dependencies {
    classpath "com.ewerk.gradle.plugins:integration-test-plugin:1.0.5"
  }
}

apply plugin: "java"
apply plugin: "com.ewerk.gradle.plugins.integration-test"

integrationTest {
  useTestNG()

  minHeapSize = "128m"
  maxHeapSize = "512m"
}
```