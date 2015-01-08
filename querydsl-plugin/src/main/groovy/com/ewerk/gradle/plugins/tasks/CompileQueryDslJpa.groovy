package com.ewerk.gradle.plugins.tasks

/**
 * A java compile task that activates the Querydsl JPA annotation processor.
 *
 * @author holgerstolzenberg
 * @since 1.0.1
 */
class CompileQuerydslJpa extends AbstractCompileQuerydsl {

  public static final String PROCESSOR = "com.mysema.query.apt.jpa.JPAAnnotationProcessor"

  @Override
  String processor() {
    return PROCESSOR
  }
}
