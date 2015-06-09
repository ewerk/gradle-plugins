package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.AnnotationProcessorPlugin
import com.ewerk.gradle.tasks.CleanAnnotationProcessorGeneratedDir
import com.ewerk.gradle.tasks.InitAnnotationProcessorGeneratedDir
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.notNullValue
import static org.hamcrest.MatcherAssert.assertThat

class AnnotationProcessorTaskTest {

  private Project project;

  private InitAnnotationProcessorGeneratedDir initTask;
  private CleanAnnotationProcessorGeneratedDir cleanTask;

  @BeforeMethod
  public void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(AnnotationProcessorPlugin.class)
    project.extensions.annotationProcessor.library = "com.querydsl:querydsl-apt:4.0.0"
    project.extensions.annotationProcessor.processor = "com.querydsl.apt.morphia.MorphiaAnnotationProcessor"
    project.evaluate()
    initTask = project.tasks.initAnnotationProcessorSourcesDir as InitAnnotationProcessorGeneratedDir
    cleanTask = project.tasks.cleanAnnotationProcessorSourcesDir as CleanAnnotationProcessorGeneratedDir
  }

  @Test
  void initSourceFolders() {
    initTask.createSourceFolders()
    assertThat(project.sourceSets.annotationProcessor, notNullValue())
    File javaDir = project.sourceSets.annotationProcessor.java.srcDirs.first() as File
    assertThat(javaDir.name, equalTo("java"))
  }

  @Test
  public void taskGroup() {
    assertThat(cleanTask.group, equalTo(AnnotationProcessorPlugin.TASK_GROUP));
    assertThat(initTask.group, equalTo(AnnotationProcessorPlugin.TASK_GROUP));
  }
}