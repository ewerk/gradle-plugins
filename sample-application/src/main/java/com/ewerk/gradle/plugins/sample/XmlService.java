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

import static java.nio.charset.Charset.forName;
import static java.util.UUID.randomUUID;
import static javax.xml.bind.JAXBContext.newInstance;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * @author holgerstolzenberg
 * @since 1.0.2
 */
public class XmlService {
  private final Marshaller marshaller;

  XmlService() throws JAXBException {
    this.marshaller = newInstance(Request.class).createMarshaller();
  }

  String createRequest() {
    final Request request = new Request();
    request.setReqId(randomUUID().toString());

    try (final ByteArrayOutputStream stream = new ByteArrayOutputStream(100)) {
      marshaller.marshal(request, stream);
      return asString(stream);
    } catch (final IOException | JAXBException e) {
      throw new RuntimeException("Error marshalling request xml.", e);
    }
  }

  private String asString(final ByteArrayOutputStream stream) {
    return new String(stream.toByteArray(), forName("UTF-8"));
  }
}
