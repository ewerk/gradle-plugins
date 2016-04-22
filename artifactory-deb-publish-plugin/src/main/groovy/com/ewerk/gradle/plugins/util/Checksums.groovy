package com.ewerk.gradle.plugins.util

import java.security.MessageDigest

/**
 * Utility class for creating file checksums.
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
final class Checksums {
  public static String sha1(byte[] payload) {
    return MessageDigest.getInstance("sha1").
        digest(payload).
        collect { String.format("%02x", it) }.join()
  }
}
