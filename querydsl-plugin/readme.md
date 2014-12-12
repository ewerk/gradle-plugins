### Auto-Value plugin

<!--
[ ![Download](https://api.bintray.com/packages/ewerk/gradle-plugins/auto-value-plugin/images/download.svg) ](https://bintray.com/ewerk/gradle-plugins/auto-value-plugin/_latestVersion)
-->

#### Description

This plugin makes it easy to generate [Querydsl](http://www.querydsl.com/) 
classes within the project.

Please have a look at the plugins [change log](change_log.md).

The plugin is currently under development, usage examples will be available soon.

#### Examples

__Use via Gradle plugin portal__

Coming soon.

<!--
```groovy
plugins {
  id "com.ewerk.gradle.plugins.querydsl" version "1.0.0"
}
```
-->

__Use via JCenter__

Coming soon.

<!--

```groovy
buildscript {
  repositories {
    jcenter()
  }

  dependencies {
    classpath "com.ewerk.gradle.plugins:auto-value-plugin:1.0.1"
  }
}

apply plugin: "com.ewerk.gradle.plugins.auto-value"

// the following closure demonstrates the extension defaults and is not necessary
autoValue {
  library = "com.google.auto.value:auto-value:1.0-rc2"
  generatedSourcesDir = "src/auto-value/java"
}
```

-->