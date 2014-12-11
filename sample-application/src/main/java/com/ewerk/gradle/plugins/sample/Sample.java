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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dummy java code for sample application.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
public final class Sample {
  private static final Logger LOG = LoggerFactory.getLogger(Sample.class);

  public Bean bean() {
    Bean bean = Bean.build("This is just a String!", 10);
    LOG.info("Hello: {}", bean);
    return bean;
  }
}
