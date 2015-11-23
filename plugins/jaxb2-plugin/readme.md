### Querydsl plugin

#### Description

This plugin makes it easy to … TODO

Please have a look at the plugins [change log](change_log.md).

#### Configuration

##### library
TODO

#### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.jaxb2" version "1.0.0"
}
```

__Use via JCenter__

```groovy
buildscript {
  repositories {
    jcenter()
  }

  dependencies {
    classpath 'com.ewerk.gradle.plugins:jaxb2-plugin:1.0.0'
  }
}

apply plugin: 'com.ewerk.gradle.plugins.jaxb2'

jaxb2 {
  xjc {
    "some-name" {
      generatedSourcesDir = 'src/generated/java'
      basePackage = 'org.any.app.model'
      schema = 'relative/path/to/schema.xsd'
    }
  }
}
```

__Full configuration example__

```groovy
plugins {
  id 'com.ewerk.gradle.plugins.jaxb2' version '1.0.0'
}

repositories {
  mavenCentral()
}

dependencies {
  jaxb2 'org.jvnet.jaxb2_commons:jaxb2-basics-runtime:0.9.5'
  jaxb2 'org.jvnet.jaxb2_commons:jaxb2-basics-ant:0.9.5'
  jaxb2 'org.jvnet.jaxb2_commons:jaxb2-basics:0.9.5'

  jaxb2 'com.sun.xml.bind:jaxb-core:2.2.11'
  jaxb2 'com.sun.xml.bind:jaxb-xjc:2.2.11'
  jaxb2 'com.sun.xml.bind:jaxb-impl:2.2.11'
}

jaxb2 {
  xjc {
    'request-classes' {
      basePackage = 'com.any.app.model.request'
      schema = 'src/main/xsd/request.xsd'
    }
  }

  xjc {
    'response-classes' {
      basePackage = 'com.any.app.model.response'
      schema = 'src/main/xsd/response.xsd'
    }
  }
}
```