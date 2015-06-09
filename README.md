[![License](http://img.shields.io/badge/license-Apache 2.0-brightgreen.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) [![Build Status](http://img.shields.io/travis/ewerk/gradle-plugins.svg?style=flat)](https://travis-ci.org/ewerk/gradle-plugins)

# EWERK Gradle Plugins
## Introduction
This repository was introduced in order to use the Github/Gradle Plugin Portal
capabilities for publishing open source Gradle plugins and using them with the new plugin
declaration syntax introduced in Gradle 2.1.

The plugins are released using the [plugin-publish](https://plugins.gradle.org/plugin/com.gradle.plugin-publish) plugin.
Prior to the actual publication process, the plugins where hosted at Bintray organizational
account 'ewerk'. This account and all associated repositories have been deleted, as the plugins
are now directly hosted at the plugin portal itself.

The plugins are based on Gradle ≥ 2.1.

## Plugins
* [annotation-processor-plugin] (https://github.com/ewerk/gradle-plugins/tree/master/annotation-processor-plugin)
* [artifactory-deb-publish-plugin] (https://github.com/ewerk/gradle-plugins/tree/master/artifactory-deb-publish-plugin)
* [auto-value-plugin] (https://github.com/ewerk/gradle-plugins/tree/master/auto-value-plugin)
* [dagger-plugin] (https://github.com/ewerk/gradle-plugins/tree/master/dagger-plugin)
* [integration-test-plugin] (https://github.com/ewerk/gradle-plugins/tree/master/integration-test-plugin)
* [querydsl-plugin] (https://github.com/ewerk/gradle-plugins/tree/master/querydsl-plugin)

Please have a look at the `sample-application` module providing a code example on how to use the
plugins.