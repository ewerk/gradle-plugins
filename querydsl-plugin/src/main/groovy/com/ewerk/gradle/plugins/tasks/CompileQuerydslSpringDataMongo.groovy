package com.ewerk.gradle.plugins.tasks

/**
 * A java compile task that activates the Spring Data Mongo Querydsl annotation processor.
 *
 * @author holgerstolzenberg
 * @since 1.0.1
 */
class CompileQuerydslSpringDataMongo extends AbstractCompileQuerydsl {

  public static
  final String PROCESSOR = "org.springframework.data.mongodb.repository.support.MongoAnnotationProcessor"

  @Override
  String processor() {
    return PROCESSOR
  }
}
