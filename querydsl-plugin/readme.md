### Querydsl plugin

[ ![Download](https://api.bintray.com/packages/ewerk/gradle-plugins/querydsl-plugin/images/download.svg) ](https://bintray.com/ewerk/gradle-plugins/querydsl-plugin/_latestVersion)

#### Description

This plugin makes it easy to generate [Querydsl](http://www.querydsl.com/) 
classes within a project. Different annotation processors can be activated via the plugins
configuration. The plugin will not manage 3rd party libraries. It is still up to the end user 
to add the required dependencies like Hibernate, Spring Data Mongo and the needed Querydsl libs.

Please have a look at the plugins [change log](change_log.md).

#### Configuration

##### library
The artifact coordinates of the Querydsl annotation processor library. 

Defaults to `com.mysema.querydsl:querydsl-apt:3.6.0`.

##### querydslSourcesDir
The project relative path to where the querydsl meta model sources are created in. It does not 
matter which annotation processors are used, all meta model classes will be created within this 
directory.

Defaults to `src/querydsl/java`.

##### jpa
Boolean flag to indicate if creation of meta model from JPA annotated sources 
should be enabled.
If so, a java compile task that enables the `com.mysema.query.apt.jpa.JPAAnnotationProcessor` will 
be added and used within the project.

Defaults to `false`.

##### jdo
Boolean flag to indicate if creation of meta model from JDO annotated sources 
should be enabled.
If so, a java compile task that enables the `com.mysema.query.apt.jdo.JDOAnnotationProcessor` will 
be added and used within the project.

Defaults to `false`.

##### hibernate
Boolean flag to indicate if creation of meta model from Hibernate annotated sources 
should be enabled.
If so, a java compile task that enables the `com.mysema.query.apt.hibernate.HibernateAnnotationProcessor` will 
be added and used within the project.

Defaults to `false`.

##### morphia
Boolean flag to indicate if creation of meta model from Morphia annotated sources 
should be enabled.
If so, a java compile task that enables the `com.mysema.query.apt.morphia.MorphiaAnnotationProcessor` 
will be added and used within the project.

Defaults to `false`.

##### roo
Boolean flag to indicate if creation of meta model from Spring Roo annotated sources 
should be enabled.
If so, a java compile task that enables the `com.mysema.query.apt.roo.RooAnnotationProcessor` will 
be added and used within the project.

Defaults to `false`.

##### springDataMongo
Boolean flag to indicate if creation of meta model from Spring Data Mongo annotated sources 
should be enabled.
If so, a java compile task that enables the `com.mysema.query.apt.roo.RooAnnotationProcessor` will 
be added and used within the project.

Defaults to `false`.

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
    classpath "com.ewerk.gradle.plugins:querydsl-plugin:1.0.1"
  }
}

apply plugin: "com.ewerk.gradle.plugins.querydsl"

// the following closure demonstrates some of the configuration defaults and is not necessary
querydsl {
  library = "com.mysema.querydsl:querydsl-apt:3.6.0"
  querydslSourcesDir = "src/querydsl/java"
}
```

__Mongo example__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.querydsl" version "1.0.1"
}

repositories {
  mavenCentral()
}

dependencies {
  […]
  compile "org.springframework.data:spring-data-mongodb:1.6.1.RELEASE" // needed to make MongoDB annotation processor available at classpath
  compile "com.mysema.querydsl:querydsl-mongodb:3.6.0" // needed to use Querydsl against MongoDB
  […]
}

querydsl {
  springDataMongo = true
}
```