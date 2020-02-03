### JAXB2 plugin

#### Description

This plugin makes it easy to generate Java source code from XML schema files (.xsd). Internally
the plugin relies on the JAXB2 ant task for generating the code. The examples below show how 
the plugin can be used.

At the moment the plugin is simplistic and just supports creating Java code from XSD. JAXB2
specific special stuff like binding files or additional task parameters are currently not 
supported but can surely make it into the plugin in future. 

Please have a look at the plugins [change log](change_log.md).

#### Dependency management
The plugin creates a own 'jaxb2' configuration and adds it to the project. Then it adds all needed
dependencies to execute the JAXB ant task. You may need to extend are change these dependencies.
As we have the `jaxb2` configuration, you can do this by using the default `dependencies {}` 
closure of your project. This is shown in the examples below.

#### Configuration

##### taskName
The full qualified name of the JAXB2 ant task the does the real work. 
Defaults to `org.jvnet.jaxb2_commons.xjc.XJC2Task`. 
Normally there will be need to change this.

##### xjc
This is the container for configuring the distinct generation steps. It can be repeated as needed
within the `jaxb2` extension. The container structure is as follows:

```groovy
xjc {
  'generation-step-name' {
    // optional, defaults to src/generated/java
    generatedSourcesDir = 'any/relative/path'
    
    // full qualified base package for the classes to be generated
    basePackage = 'com.any.app'
    
    // relative path the XSD file to generate the code from
    schema = 'src/main/xsd/any-file.xsd'
    
    // relative path to directory including binding files
    // all files of pattern **/*.xjb will be included
    // default to null (no binding files used)
    // Optional, can be left away
    bindingsDir = 'src/main/xsb'
    
    // comma separated list of binding file includes
    // falls back to '**/*.xjb' if not specified
    includedBindingFiles = 'any.xjb, subdir/test.xjb'
    
    additionalArgs = '-nv -dtd'

    // output encoding of the generated files
    encoding = 'UTF-8'
  }
}
```

#### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.jaxb2" version "1.0.10"
}
```

__Full configuration example__

```groovy
plugins {
  id 'com.ewerk.gradle.plugins.jaxb2' version '1.0.10'
}

repositories {
  mavenCentral()
}

// the whole dependency container could be left out
dependencies {
  // default JAXB2 libs added by the plugin
  jaxb2 'org.jvnet.jaxb2_commons:jaxb2-basics-runtime:0.9.5'
  jaxb2 'org.jvnet.jaxb2_commons:jaxb2-basics-ant:0.9.5'
  jaxb2 'org.jvnet.jaxb2_commons:jaxb2-basics:0.9.5'

  // default JAXB libs added by the plugin
  jaxb2 'com.sun.xml.bind:jaxb-core:2.2.11'
  jaxb2 'com.sun.xml.bind:jaxb-xjc:2.2.11'
  jaxb2 'com.sun.xml.bind:jaxb-impl:2.2.11'
  
  // any custom libraries here
  jaxb2 'com. …'
}

jaxb2 {
  xjc {
    'request-classes' {
      basePackage = 'com.any.app.model.request'
      schema = 'src/main/xsd/request.xsd'
      encoding = 'UTF-8'
      extension = true 
    }
  }

  xjc {
    'response-classes' {
      basePackage = 'com.any.app.model.response'
      schema = 'src/main/xsd/response.xsd'
      bindings = 'src/main/xsb'
    }
  }
}

```

__Minimal configuration example__
```groovy
plugins {
  id 'com.ewerk.gradle.plugins.jaxb2' version '1.0.10'
}

repositories {
  mavenCentral()
}

jaxb2 {
  xjc {
    'request-classes' {
      basePackage = 'com.any.app.model.request'
      schema = 'src/main/xsd/request.xsd'
    }
  }
}
```
