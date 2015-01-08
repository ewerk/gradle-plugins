package com.ewerk.gradle.plugins.tasks

/**
 * A java compile task that activates the Querydsl JDO annotation processor.
 *
 * @author holgerstolzenberg
 * @since 1.0.1
 */
class CompileQuerydslJdo extends AbstractCompileQuerydsl {

  public static final String PROCESSOR = "com.mysema.query.apt.jdo.JDOAnnotationProcessor"

  @Override
  String processor() {
    return PROCESSOR
  }
}
