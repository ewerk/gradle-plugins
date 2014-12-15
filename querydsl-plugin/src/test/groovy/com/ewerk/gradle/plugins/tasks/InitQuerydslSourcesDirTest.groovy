package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.QuerydslPlugin
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.notNullValue
import static org.hamcrest.MatcherAssert.assertThat

/**
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class InitQuerydslSourcesDirTest {

  private Project project;

  private InitQuerydslSourcesDir createTask;

  @BeforeMethod
  public void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(QuerydslPlugin.class)
    project.evaluate()

    createTask = project.tasks.initQuerydslSourcesDir as InitQuerydslSourcesDir
  }

  @Test
  void testCreateSourceFolders() {
    createTask.createSourceFolders()
    assertThat(project.sourceSets.querydsl, notNullValue())

    File javaDir = project.sourceSets.querydsl.java.srcDirs.first() as File
    assertThat(javaDir.name, equalTo("java"))
  }

  @Test
  public void testGroup() {
    assertThat(createTask.group, equalTo(QuerydslPlugin.TASK_GROUP));
  }

  @Test
  public void testDescription() {
    assertThat(createTask.description, equalTo(InitQuerydslSourcesDir.DESCRIPTION));
  }
}
