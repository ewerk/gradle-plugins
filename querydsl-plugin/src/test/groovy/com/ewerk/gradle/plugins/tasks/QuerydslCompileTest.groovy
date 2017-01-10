/*
 * Copyright 2015 the original author or authors.
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
package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.QuerydslPlugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceTask
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat

/**
 * @author Illya Boyko
 */
class QuerydslCompileTest {

  private Project project
  private SourceTask compileTask

  QuerydslCompileTest() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(QuerydslPlugin.class)
    compileTask = project.tasks.compileQuerydsl as SourceTask
    compileTask.includes += ['**/entities/*.java']
    project.evaluate()

  }

  @Test
  void testIncludes() {
    assertThat(compileTask.getIncludes(), hasItem(is('**/entities/*.java')))
  }
}