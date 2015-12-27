### Dagger plugin

#### Description

"Dagger2 dependency injection - The guiding principle is to generate code that mimics the code that a user might have hand-written."

This plugin uses the [Dagger2](http://google.github.io/dagger/) compiler to process annotations that are compatible with JSR-330.

Gradle Java/Android plugin support (imported into Intellij/Android Studio).

[This plugins change log](change_log.md).

#### Configuration

##### daggerSourcesDir

The destination directory for the generated Dagger java source.

Defaults to 'src/dagger/java' for Java Plugin projects.

The daggerSourcesDir is deleted when executing the clean task; for this reason, a BuildException will be thrown if
daggerSourcesDir is located as your main java sources.

Android projects always use '${buildDir}/generated/source/dagger/<variant>' to maintain Android convention.

##### library

The dependency artifact for the compile/runtime usage of Dagger.
This is added to the compile configuration for the project.

Defaults to 'com.google.dagger:dagger:2.0'.

##### processorLibrary

The dependency artifact for the compile/runtime usage of Dagger.

Defaults to 'com.google.dagger:dagger-compiler:2.0'.

#### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.dagger" version "1.0.1"
}
```

__Use via JCenter__

```groovy
buildscript {
  repositories {
    jcenter()
  }

  dependencies {
    classpath "gradle.plugin.com.ewerk.gradle.plugins:dagger-plugin:1.0.1"
  }
}

apply plugin: 'com.ewerk.gradle.plugins.dagger'
```

```groovy
// the following closure demonstrates some of the configuration defaults and is not necessary
dagger {
  library = "com.google.dagger:dagger:2.0"
  processorLibrary = "com.google.dagger:dagger-compiler:2.0"
  daggerSourcesDir = "src/dagger/java"
}
```