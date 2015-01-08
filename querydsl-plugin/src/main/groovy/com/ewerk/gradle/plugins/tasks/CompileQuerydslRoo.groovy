package com.ewerk.gradle.plugins.tasks

/**
 * A java compile task that activates the Querydsl Spring Roo annotation processor.
 *
 * @author holgerstolzenberg
 * @since 1.0.1
 */
class CompileQuerydslRoo extends AbstractCompileQuerydsl {

  public static final String PROCESSOR = "com.mysema.query.apt.roo.RooAnnotationProcessor"

  @Override
  String processor() {
    return PROCESSOR
  }
}
