/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ewerk.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.testing.Test

/**
 * The integration test plugin can be used to execute integration tests from a own sourceSet named
 * "integration". Just place your integration tests and resources under
 * "src/integration/[java|resources]" and then run the "integrationTest" task. This allows
 * for running unit and integration test in separate without the need to make a specific
 * configuration to each involved build script.
 *
 * The plugin will force the core 'java' plugin to be applied if not present. This is necessary
 * for having sourceSets available.
 *
 * @see JavaPlugin
 * @since 1.0.0
 * @author holgerstolzenberg
 */
class IntegrationTestPlugin implements Plugin<Project> {

  static final String GROUP = "Verification"

  @Override
  void apply(final Project project) {

    if (project.plugins.hasPlugin(IntegrationTestPlugin.class)) {
      return;
    }

    if (!project.plugins.hasPlugin(JavaPlugin.class)) {
      project.plugins.apply(JavaPlugin.class)
    }

    project.sourceSets {
      integration {
        java.srcDir project.file("src/integration/java")
        resources.srcDir project.file("src/integration/resources")
      }
    }

    project.dependencies {
      integrationCompile project.configurations.testCompile
      integrationRuntime project.configurations.testRuntime

      integrationCompile project.sourceSets.main.output
      integrationCompile project.sourceSets.test.output
    }

    project.task("integrationTest", type: Test, description: "Runs the integration tests.",
        group: GROUP) {
      testClassesDir = project.sourceSets.integration.output.classesDir
      classpath = project.sourceSets.integration.runtimeClasspath
    }

    project.task("allTests", dependsOn: [project.test, project.integrationTest],
        description: "Runs all tests.", group: GROUP) {
    }
  }
}