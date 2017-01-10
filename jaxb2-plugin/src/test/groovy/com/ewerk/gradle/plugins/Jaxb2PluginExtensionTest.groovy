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

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.notNullValue
import static org.hamcrest.MatcherAssert.assertThat

/**
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class Jaxb2PluginExtensionTest {

  private Jaxb2PluginExtension extension

  @BeforeMethod
  void setup() {
    extension = new Jaxb2PluginExtension()
  }

  @Test
  void testTaskNameIsSet() {
    assertThat(extension.taskName, notNullValue())
  }
}