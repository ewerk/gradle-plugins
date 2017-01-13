### Dagger plugin

#### Description

"Dagger2 dependency injection - The guiding principle is to generate code that mimics the code that a user might have hand-written."

This plugin uses the [Dagger2](http://google.github.io/dagger/) compiler to process annotations that are compatible with `JSR-330`.

Gradle Java/Android plugin support (imported into Intellij/Android Studio).

[This plugins change log](change_log.md).

#### Building the plugin
Some of the plugin's unit tests need an installed Android SDK in order to test plugin compatibility within
an Android project. 

##### Installing the SDK
The SDK can be downloaded here: https://developer.android.com/studio/index.html#downloads

You just need to download the tools distribution. After set point `$ANDROID_HOME` to the install location 
and use `android` or `sdkmanger` command to install the Android distribution needed.
 
##### Package managers
Alternatively the Android SDK can be installed using different package managers. 
On Mac you can use [Homebrew](http://brew.sh/index_de.html) (Mac).

`brew install android-sdk`

#### Configuration

##### daggerSourcesDir
The destination directory for the generated Dagger java source.
Defaults to `src/dagger/java` for Java Plugin projects.

The `daggerSourcesDir` is deleted when executing the clean task; for this reason, a BuildException will be thrown if
`daggerSourcesDir` is located as your main java sources.

Android projects always use `${buildDir}/generated/source/dagger/<variant>` to maintain Android convention.

##### library
The dependency artifact for the compile/runtime usage of Dagger.
This is added to the compile configuration for the project.

Defaults to `com.google.dagger:dagger:2.6.1`.

##### processorLibrary
The dependency artifact for the compile/runtime usage of Dagger.

Defaults to `com.google.dagger:dagger-compiler:2.6.1`.

#### Examples
__Use via Gradle plugin portal__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.dagger" version "1.0.4"
}

// the following closure demonstrates some of the configuration defaults and is not necessary
dagger {
  library = "com.google.dagger:dagger:2.6.1"
  processorLibrary = "com.google.dagger:dagger-compiler:2.6.1"
  daggerSourcesDir = "src/dagger/java"
}
```