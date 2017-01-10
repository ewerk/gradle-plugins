package com.ewerk.gradle.plugins.util

import com.ewerk.gradle.plugins.PublishException
import com.ewerk.gradle.plugins.tasks.PushDebToArtifactory
import com.squareup.okhttp.*
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

/**
 * Service class that provides HTTP(S) connectivity to a configured Artifactory REST API endpoint.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class ArtifactoryHttpClient {
  static final MediaType BINARY = MediaType.parse("application/octet-stream; charset=utf-8")

  static final String APPLICATION_VND_JFROG_ITEM_CREATED =
      'application/vnd.org.jfrog.artifactory.storage.ItemCreated+json'

  private static final Logger LOG = Logging.getLogger(PushDebToArtifactory.class)

  private final OkHttpClient client

  final String baseUrl, user, password, repoKey, name, component, distribution, arch

  ArtifactoryHttpClient(String baseUrl, String user, String password, String repoKey,
                        String name, String component, String distribution, String arch) {
    this.baseUrl = baseUrl
    this.user = user
    this.password = password
    this.repoKey = repoKey
    this.name = name
    this.component = component
    this.distribution = distribution
    this.arch = arch

    client = new OkHttpClient()
  }

  void publish(File theFile) {

    byte[] fileData = theFile.readBytes()
    String prefix = Urls.prefix(name)

    Request.Builder requestBuilder = new Request.Builder()
        .url(Urls.construct(baseUrl, repoKey, name, component, prefix, distribution, arch))
        .header('Accept', APPLICATION_VND_JFROG_ITEM_CREATED)
        .header('X-Checksum-Sha1', Checksums.sha1(fileData))
        .put(RequestBody.create(BINARY, fileData))

    if (user != null && !user.isEmpty()) {
      requestBuilder.header("Authorization", Credentials.basic(user, password))
    }

    Response response = client.newCall(requestBuilder.build()).execute()
    dumpResponse(response)

    int code = response.code()

    if (code == HttpCode.UNAUTHORIZED) {
      throw new PublishException("Authentication required.")
    }

    if (code != HttpCode.CREATED) {
      throw new PublishException("Publication failed.")
    }
  }

  private static void dumpResponse(Response response) {
    LOG.info("--------------------------------------------------------------------------------")
    LOG.info("Artifactory response")
    LOG.info("Artifactory: {}", response.code())
    LOG.info("Artifactory: {}", response.headers())
    LOG.info("Artifactory: {}", response.body().string())
    LOG.info("--------------------------------------------------------------------------------")
  }

  private static final class HttpCode {
    static final int CREATED = 201
    static final int UNAUTHORIZED = 401
  }
}
