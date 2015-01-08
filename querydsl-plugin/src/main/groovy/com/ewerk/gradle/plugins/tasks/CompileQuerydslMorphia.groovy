package com.ewerk.gradle.plugins.tasks

/**
 * A java compile task that activates the Querydsl Morphia annotation processor.
 *
 * @author holgerstolzenberg
 * @since 1.0.1
 */
class CompileQuerydslMorphia extends AbstractCompileQuerydsl {

  public static final String PROCESSOR = "com.mysema.query.apt.morphia.MorphiaAnnotationProcessor"

  @Override
  String processor() {
    return PROCESSOR
  }
}
