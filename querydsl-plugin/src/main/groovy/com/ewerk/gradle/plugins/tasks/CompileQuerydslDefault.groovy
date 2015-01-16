package com.ewerk.gradle.plugins.tasks

/**
 * A java compile task that activates the default Querydsl annotation processor.
 * @author griffio
 * @since 1.0.2
 */
class CompileQuerydslDefault extends AbstractCompileQuerydsl {

  public static final String PROCESSOR = "com.mysema.query.apt.QuerydslAnnotationProcessor"

  @Override
  String processor() {
    return PROCESSOR
  }
}
