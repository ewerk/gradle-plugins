package com.ewerk.gradle.plugins.tasks

/**
 * A java compile task that activates the Querydsl Hibernate annotation processor.
 *
 * @author holgerstolzenberg
 * @since 1.0.1
 */
class CompileQuerydslHibernate extends AbstractCompileQuerydsl {

  public static final String PROCESSOR = "com.mysema.query.apt.hibernate.HibernateAnnotationProcessor"

  @Override
  String processor() {
    return PROCESSOR
  }
}
