[![Build Status](http://img.shields.io/travis/ewerk/gradle-plugins.svg?style=flat)](https://travis-ci.org/ewerk/gradle-plugins) [![License](http://img.shields.io/badge/license-Apache-brightgreen.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

# EWERK Gradle Plugins
## Introduction
This repository was introduced in order to use the Github/Bintray/Gradle Plugin Portal
capabilities for publishing open source Gradle plugins and using them with the new plugin
declaration syntax introduced in Gradle 2.1.  

## Plugins
### Integration test plugin
The plugin can be used to execute integration tests from a sophisticated sourceSet. Just place
your integration tests and resources under `src/integration/java` and `src/integration/resources`
and run task `integrationTest`

__Example__
A full example will be provided after the plugin has been published to the 
[Gradle Plugin Portal](http://plugins.gradle.org).