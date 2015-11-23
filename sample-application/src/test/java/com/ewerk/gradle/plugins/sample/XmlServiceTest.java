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
package com.ewerk.gradle.plugins.sample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

/**
 * @author holgerstolzenberg
 * @since 1.0.2
 */
public class XmlServiceTest {
  private static final Logger LOG = getLogger(XmlServiceTest.class);

  @Test
  public void testCreateRequest() throws JAXBException {
    final XmlService xmlService = new XmlService();
    final String request = xmlService.createRequest();

    LOG.info("Request-XML: {}", request);
    assertThat(request).isNotEmpty();
  }
}