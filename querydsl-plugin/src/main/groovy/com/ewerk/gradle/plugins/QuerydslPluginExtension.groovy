package com.ewerk.gradle.plugins

/**
 * DLS extension for the Querydsl plugin. Provides some convenient configuration options.
 *
 * The processor configuration and processor classes are defined in this location.
 *
 * @author holgerstolzenberg , griffio
 * @since 1.0.0
 */
class QuerydslPluginExtension {

  static String HIBERNATE_PROC = "com.querydsl.apt.hibernate.HibernateAnnotationProcessor"
  static String JDO_PROC = "com.querydsl.apt.jdo.JDOAnnotationProcessor"
  static String JPA_PROC = "com.querydsl.apt.jpa.JPAAnnotationProcessor"
  static String MORPHIA_PROC = "com.querydsl.apt.morphia.MorphiaAnnotationProcessor"
  static String QUERYDSL_PROC = "com.querydsl.apt.QuerydslAnnotationProcessor"
  static String ROO_PROC = "com.querydsl.apt.roo.RooAnnotationProcessor"
  static String SPRING_DATA_MONGO_PROC = "org.springframework.data.mongodb.repository.support.MongoAnnotationProcessor"

  static final String NAME = "querydsl"
  static final String DEFAULT_QUERYDSL_SOURCES_DIR = "src/querydsl/java"
  static final String DEFAULT_LIBRARY = "com.querydsl:querydsl-apt:4.0.9"

  String querydslSourcesDir = DEFAULT_QUERYDSL_SOURCES_DIR
  String library = DEFAULT_LIBRARY

  boolean jpa = false;
  boolean jdo = false;
  boolean hibernate = false;
  boolean morphia = false;
  boolean roo = false;
  boolean springDataMongo = false;
  boolean querydslDefault = false;

  String processors() {

    List processors = []

    if (hibernate) {
      processors << HIBERNATE_PROC
    }

    if (jdo) {
      processors << JDO_PROC
    }

    if (jpa) {
      processors << JPA_PROC
    }

    if (morphia) {
      processors << MORPHIA_PROC
    }

    if (roo) {
      processors << ROO_PROC
    }

    if (querydslDefault) {
      processors << QUERYDSL_PROC
    }

    if (springDataMongo) {
      processors << SPRING_DATA_MONGO_PROC
    }

    return processors.join(",")
  }
}
