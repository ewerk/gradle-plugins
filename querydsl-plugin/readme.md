### Querydsl plugin

[ ![Download](https://api.bintray.com/packages/ewerk/gradle-plugins/querydsl-plugin/images/download.svg) ](https://bintray.com/ewerk/gradle-plugins/querydsl-plugin/_latestVersion)

#### Description

This plugin makes it easy to generate [Querydsl](http://www.querydsl.com/) 
classes within a project.

Please have a look at the plugins [change log](change_log.md).

#### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.querydsl" version "1.0.0"
}
```

__Use via JCenter__

```groovy
buildscript {
  repositories {
    jcenter()
  }

  dependencies {
    classpath "com.ewerk.gradle.plugins:querydsl-plugin:1.0.0"
  }
}

apply plugin: "com.ewerk.gradle.plugins.querydsl"

// the following closure demonstrates the extension defaults and is not necessary
querydsl {
  library = "com.mysema.querydsl:querydsl-apt:3.6.0"
  querydslSourcesDir = "src/querydsl/java"
}
```