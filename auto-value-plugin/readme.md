### Auto-Value plugin

#### Description

This plugin makes it easy to generate [Google Auto-Value](https://github.com/google/auto/tree/master/value) 
classes within a project.

Because of the early stage of the plugin, the configuration (and plugin DSL) has undergone some
minor changes. This is documented in the [change log](change_log.md).

#### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.auto-value" version "1.0.1"
}
```

__Use via JCenter__

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
  autoValueSourcesDir = "src/auto-value/java"
}
```