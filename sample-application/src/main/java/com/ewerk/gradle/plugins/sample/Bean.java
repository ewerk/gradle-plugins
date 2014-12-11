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

import com.google.auto.value.AutoValue;

/**
 * Simple construct demonstrating the auto-value usage.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
@AutoValue
public abstract class Bean {
  abstract String hello();

  abstract int count();

  static Bean build(final String hello, final int count) {
    return new AutoValue_Bean(hello, count);
  }
}
