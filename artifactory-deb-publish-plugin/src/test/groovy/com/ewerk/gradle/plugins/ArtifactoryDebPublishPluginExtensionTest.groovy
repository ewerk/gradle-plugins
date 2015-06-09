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
class ArtifactoryDebPublishPluginExtensionTest {

  private ArtifactoryDebPublishPluginExtension extension;

  @BeforeMethod
  public void setup() {
    extension = new ArtifactoryDebPublishPluginExtension()
    extension.baseUrl = 'https://artifactory.com'
    extension.repoKey = "debian_repo";
    extension.distribution = "jessie";
    extension.component = "non-free";
    extension.arch = "amd64";
  }

  @Test
  public void testBaseUrlMustNotBeNull() {
    assertThat(extension.baseUrl, notNullValue());
  }

  @Test
  public void testRepoKeyMustNotBeNull() {
    assertThat(extension.repoKey, notNullValue());
  }

  @Test
  public void testDistributionMustNotBeNull() {
    assertThat(extension.distribution, notNullValue());
  }

  @Test
  public void testComponentMustNotBeNull() {
    assertThat(extension.component, notNullValue());
  }

  @Test
  public void testArchMustNotBeNull() {
    assertThat(extension.arch, notNullValue());
  }
}