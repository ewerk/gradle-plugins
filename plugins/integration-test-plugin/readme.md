### Integration test plugin

#### Description

The plugin can be used to execute integration tests from a sophisticated sourceSet. Just place
your integration tests and resources under `src/integration/java` and `src/integration/resources`
and run task `integrationTest`.

The plugin was inspired by the sources taken from [here](http://blog.lick-me.org/2014/07/fun-with-gradle-plugins-integration-tests/).

The plugin applies the task `integration-test` to the project. This tasks extends from the default
`test` task. Therefore all configuration properties from test task are available for integration 
tests also.

The `integrationTest` task extends from the `test` task and therefore the complete configuration
set of the `test` task is also available to the integration test configuration

Please see [Gradle test config](http://www.gradle.org/docs/current/dsl/org.gradle.api.tasks.testing.Test.html) 
for details.

The plugins change log can be found [here](change_log.md).

#### Examples

__Use via Gradle plugin portal__

```groovy
plugins {
  id "com.ewerk.gradle.plugins.integration-test" version "1.0.6"
}

integrationTest {
  useTestNG()

  minHeapSize = "128m"
  maxHeapSize = "512m"
}
```