[![License](http://img.shields.io/badge/license-Apache%202.0-brightgreen.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) [![Build Status](http://img.shields.io/travis/ewerk/gradle-plugins.svg?style=flat)](https://travis-ci.org/ewerk/gradle-plugins)

# EWERK Gradle Plugins

> **_NOTE:_**  The plugins are currently *not compatible to Gradle 5+* and have not been tested with a JDK higher 
               than 1.8. Currently a lot of issues arise related to using the plugins with Gradle 5+. There are 
               plans to adopt the plugins to the newest Gradle API but time is lacking. Help is pretty much 
               appreciated. Please see https://github.com/ewerk/gradle-plugins/milestone/1.


## Introduction
This repository was introduced in order to use the Github/Gradle Plugin Portal
capabilities for publishing open source Gradle plugins and using them with the new plugin
declaration syntax introduced in Gradle 2.1.

The plugins are released using the [plugin-publish](https://plugins.gradle.org/plugin/com.gradle.plugin-publish) plugin.
Prior to the actual publication process, the plugins were hosted at Bintray organizational
account 'ewerk'. This account and all associated repositories have been deleted, as the plugins
are now directly hosted at the plugin portal itself.

The plugins are designed around Java language support. At the time being a lot of issues are raised
regarding Groovy language support, which is not intended. The plugins may become compatible in the future, 
therefore help is very appreciated.

## Gradle compatibility
|Plugin|≥ 2.1|≥ 3.3|≥ 5.0| 
|---|---|---|---|
|annotation-processor-plugin|≤1.0.3|≥1.0.4|ø|
|artifactory-deb-publish-plugin|≤1.0.1|≥1.0.2|ø|
|auto-value-plugin|≤1.0.7|≥1.0.8|ø|
|dagger-plugin|≤1.0.7|≥1.0.8|ø|
|integration-test-plugin|≤1.0.8|≥1.0.9|ø|
|jaxb2-plugin|≤1.0.2|≥1.0.3|ø|
|querydsl-plugin|≤1.0.7|≥1.0.8|`INCUBATING`


## Plugins
* [annotation-processor-plugin](https://github.com/ewerk/gradle-plugins/tree/master/annotation-processor-plugin)
* [artifactory-deb-publish-plugin](https://github.com/ewerk/gradle-plugins/tree/master/artifactory-deb-publish-plugin)
* [auto-value-plugin](https://github.com/ewerk/gradle-plugins/tree/master/auto-value-plugin)
* [dagger-plugin](https://github.com/ewerk/gradle-plugins/tree/master/dagger-plugin)
* [integration-test-plugin](https://github.com/ewerk/gradle-plugins/tree/master/integration-test-plugin)
* [jaxb2-plugin](https://github.com/ewerk/gradle-plugins/tree/master/jaxb2-plugin)
* [querydsl-plugin](https://github.com/ewerk/gradle-plugins/tree/master/querydsl-plugin)

Please have a look at the [Samples](https://github.com/ewerk/gradle-plugins-samples) providing code examples on how to use the plugins.
