# How Do I Contribute?

You can participate to this project and sub projects with any [issues](https://github.com/ewerk/gradle-plugins/issues) and where [pull requests](https://github.com/ewerk/gradle-plugins/pulls) are also welcome, we have some helpful guidelines to follow.

### Plugin Development

When contributing to plugin development, you should fork from the main project and create a new feature branch for your own commits. When you are done create a [pull request](https://help.github.com/articles/using-pull-requests/) to [upstream master](https://github.com/ewerk/gradle-plugins/tree/master).
If you are creating a PR from an issue, see [closing issues via commit messages](https://help.github.com/articles/closing-issues-via-commit-messages/).

 **Some guidelines**
* Plugins are developed for Java 1.8 compatibility
* Please review all existing plugins for naming conventions and source code layout
* Java source code in this project is currently indented with 2 spaces
* Update any plugin README.md or CHANGE_LOG.md to reflect the latest values that may now be relevant after your changes
* Create a minimal JUnit test and possibly add an example to [gradle-plugins-samples](https://github.com/ewerk/gradle-plugins-samples)

**Development Setup**

From your plugin development branch, your latest changes can be installed to your local maven (.m2) repository with a version labeled at the SNAPSHOT build located in the gradle.properties.

To do this, use the **install** task
```
../gradlew build install
```

It is recommended to test any changes against the [gradle-plugins-samples](https://github.com/ewerk/gradle-plugins-samples)

### How do I setup a local plugin?

In your own project or, for example, in the samples build.gradle, remove [1] the line where the released version is applied from the Gradle plugin portal, add a buildscript [2] section using mavenLocal() as the repository and the plugin identifier to apply plugin.

Each plugin's gradle.properties maintains the current snapshot version.

**Example**

[1] Remove the current plugin portal version
```
plugins {
 // id 'com.ewerk.gradle.plugins.annotation-processor' version '1.0.2'
}
```
[2] Replace with a build script for your local snapshot version
```
buildscript {
  repositories {
    mavenLocal()
  }

  dependencies {
    classpath 'com.ewerk.gradle.plugins:annotation-processor-plugin:1.0.3-SNAPSHOT'
  }
}

apply plugin: 'com.ewerk.gradle.plugins.annotation-processor'
```
