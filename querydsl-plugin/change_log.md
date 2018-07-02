# Change log

## 1.0.0
* Initial release

## 1.0.1
* The meta model can be created from different annotation processors

## 1.0.2
* added support for com.mysema.query.apt.QuerydslAnnotationProcessor

## 1.0.3
* Internal refactoring, only one task `QuerydslCompile` is added to the project that configures all annotation processors

## 1.0.4
* Plugin portal release process changed
* Support for `providedCompile` and `providedRuntime` dependencies

## 1.0.5
* Gradle 2.6 compatibility

## 1.0.6
* Querydsl 4 library dependency is now required for this plugin.
* Querydsl 4 is required for SpringData "Hopper" JPA generation.

## 1.0.7
* Bugfix (issue #51) for compileQuerydsl task, Java compiler setting of destinationDir, must evaluate user's querydslSourcesDir preference.

## 1.0.8
* Querydsl 4 library dependency updated to (4.1.3) matching Spring Data version
* Gradle 3.3 compatibility

## 1.0.9
* Set JDK-8 as default

## 1.0.10-SNAPSHOT
* Querydsl 4 library dependency updated to (4.1.4)
* Add support for custom `aptOptions` parameter

## 1.0.11-SNAPSHOT
* Current plugin snapshot
