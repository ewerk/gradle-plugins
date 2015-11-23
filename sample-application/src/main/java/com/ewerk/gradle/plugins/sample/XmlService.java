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

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBException;

/**
 * @author holgerstolzenberg
 * @since 1.0.2
 */
public class XmlService {
  //  private final Marshaller marshaller;

  XmlService() throws JAXBException {
    //    this.marshaller = JAXBContext.newInstance(Request.class).createMarshaller();
  }

  String createRequest() {
    //    final Request request = new Request();
    //    request.setReqId(randomUUID().toString());
    //
    //    try (final ByteArrayOutputStream stream = new ByteArrayOutputStream(100)) {
    //      marshaller.marshal(request, stream);
    //      return asString(stream);
    //    } catch (final IOException | JAXBException e) {
    //      throw new RuntimeException("Error marshalling request xml.", e);
    //    }
    return "TODO";
  }

  private String asString(final ByteArrayOutputStream stream) {
    return new String(stream.toByteArray(), forName("UTF-8"));
  }
}
