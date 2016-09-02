### Querydsl plugin

#### Description

This plugin makes it easy to generate [Querydsl](http://www.querydsl.com/)
classes within a project. Different annotation processors can be activated via the plugins
configuration. The plugin will not manage 3rd party libraries. It is still up to the end user
to add the required dependencies like Hibernate, Spring Data Mongo and the needed Querydsl libs.

Note: from release 1.0.6 this plugin requires Querydsl 4.x dependencies.

Please have a look at the plugins [change log](change_log.md).

#### Configuration

##### library
The artifact coordinates of the Querydsl annotation processor library.

Defaults to `com.querydsl:querydsl-apt:4.1.3`.

Note: this can only be a version supported by the current [Spring Data](http://projects.spring.io/spring-data/) project.

##### querydslSourcesDir
The project relative path to where the querydsl meta model sources are created in. It does not
matter which annotation processors are used, all meta model classes will be created within this
directory.

Defaults to `src/querydsl/java`.

##### jpa
Boolean flag to indicate if creation of meta model from JPA annotated sources
should be enabled.
If so, a java compile task that enables the `com.querydsl.apt.jpa.JPAAnnotationProcessor` will
be added and used within the project.

Defaults to `false`.

##### jdo
Boolean flag to indicate if creation of meta model from JDO annotated sources
should be enabled.
If so, a java compile task that enables the `com.querydsl.apt.jdo.JDOAnnotationProcessor` will
be added and used within the project.

Defaults to `false`.

##### hibernate
Boolean flag to indicate if creation of meta model from Hibernate annotated sources
should be enabled.
If so, a java compile task that enables the `com.querydsl.apt.hibernate.HibernateAnnotationProcessor` will
be added and used within the project.

Defaults to `false`.

##### morphia
Boolean flag to indicate if creation of meta model from Morphia annotated sources
should be enabled.
If so, a java compile task that enables the `com.querydsl.apt.morphia.MorphiaAnnotationProcessor`
will be added and used within the project.

Defaults to `false`.

##### roo
Boolean flag to indicate if creation of meta model from Spring Roo annotated sources
should be enabled.
If so, a java compile task that enables the `com.querydsl.apt.roo.RooAnnotationProcessor` will
be added and used within the project.

Defaults to `false`.

##### springDataMongo
Boolean flag to indicate if creation of meta model from Spring Data Mongo annotated sources
should be enabled.
If so, a java compile task that enables the `com.querydsl.apt.roo.RooAnnotationProcessor` will
be added and used within the project.

Defaults to `false`.

##### querydslDefault
Boolean flag to indicate if creation of meta model from Querydsl annotated sources
should be enabled.
If so, a java compile task that enables the `com.querydsl.apt.QuerydslAnnotationProcessor` will
be added and used within the project.

Defaults to `false`.

#### Examples

__Mongo example__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.querydsl" version "1.0.8"
}

repositories {
  mavenCentral()
}

dependencies {
  […]
  // make MongoDB annotation processor available at classpath
  compile "org.springframework.data:spring-data-mongodb:1.9.1.RELEASE"

  // use Querydsl against MongoDB
  compile "com.querydsl.apt:querydsl-mongodb:4.1.3"
  […]
}

querydsl {
  springDataMongo = true
}
```
