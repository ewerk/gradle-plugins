### Auto-Value plugin

#### Description

This plugin makes it easy to generate [Google Auto-Value](https://github.com/google/auto/tree/master/value) 
classes within a project.

Because of the early stage of the plugin, the configuration (and plugin DSL) has undergone some
minor changes. This is documented in the [change log](change_log.md).

Version 1.0.3 of the plugin was accidentally build and published with JDK-8 which breaks 
compatibility to JDK-7. This was fixed in version 1.0.4.

#### Configuration

##### autoValueSourcesDir

The destination directory for the generated java sources.

Defaults to 'src/auto-value/java' for Java Plugin projects.

The autoValueSourcesDir is deleted when executing the clean task; for this reason, a BuildException will be thrown if
autoValueSourcesDir is located in your main java sources.

##### library

The dependency artifact for the compile/runtime usage of Autovalue.
This is added to the compile configuration for the project.

Defaults to 'com.google.auto.value:auto-value:1.0'.

#### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.auto-value" version "1.0.5"
}
```

__Use via JCenter__

```groovy
buildscript {
  repositories {
    jcenter()
  }

  dependencies {
    classpath "com.ewerk.gradle.plugins:auto-value-plugin:1.0.5"
  }
}

apply plugin: "com.ewerk.gradle.plugins.auto-value"

// the following closure demonstrates the extension defaults and is not necessary
autoValue {
  library = "com.google.auto.value:auto-value:1.1"
  autoValueSourcesDir = "src/auto-value/java"
}
```