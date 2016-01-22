### Annotation Processor plugin

#### Description

This plugin provides a simple integration point for an Annotation Processor capable of generating Java source.

A separate compile task that only runs the annotation processor is executed during build phase.

If the configured processor library is a required compile dependency then add '-proc:none' to your
'compileJava.options.compilerArgs', this will stop javac executing the processor using classpath discovery.

[This plugins change log](change_log.md).

#### Configuration

##### sourcesDir

This specifies the annotation processor destination directory; It will be created automatically.

Defaults to 'src/generated/java'.

##### library

This specifies dependency artifact co-ordinates or a project dependency module containing the annotation processor.
It will only be added to the classpath of the annotation process compile task

Required value.

##### processor

This specifies a fully qualified annotation processor class found in the library dependency.

Required value.

#### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.annotation-processor" version "1.0.2"
}

// the following closure demonstrates a configuration with artifact co-ordinates
annotationProcessor {
  library "com.querydsl:querydsl-apt:4.0.0"
  processor "com.querydsl.apt.morphia.MorphiaAnnotationProcessor"
  sourcesDir "src/morphia/java"
}

// the following closure demonstrates a configuration with project module dependency 
annotationProcessor {
  library project(":myproc")
  processor "com.querydsl.apt.morphia.MorphiaAnnotationProcessor"
  sourcesDir "src/morphia/java"
}

// the following processor is also required on the compile classpath
annotationProcessor {
  library "com.beust:version-processor:0.2"
  processor "com.beust.version.VersionProcessor"
}

// processor dependency for compile
compile("com.beust:version-processor:0.2")

// must disable classpath processor
compileJava.options.compilerArgs << '-proc:none'

```
