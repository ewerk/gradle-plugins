package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.AutoValuePlugin
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
class InitAutoValueSourcesDirTest {

  private Project project;

  private InitAutoValueSourcesDir createTask;

  @BeforeMethod
  public void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(AutoValuePlugin.class)
    project.evaluate()

    createTask = project.tasks.initAutoValueSourcesDir as InitAutoValueSourcesDir
  }

  @Test
  void testCreateSourceFolders() {
    createTask.createSourceFolders()
    assertThat(project.sourceSets.autoValue, notNullValue())

    File javaDir = project.sourceSets.autoValue.java.srcDirs.first() as File
    assertThat(javaDir.name, equalTo("java"))
  }

  @Test
  public void testGroup() {
    assertThat(createTask.group, equalTo(AutoValuePlugin.TASK_GROUP));
  }

  @Test
  public void testDescription() {
    assertThat(createTask.description, equalTo(InitAutoValueSourcesDir.DESCRIPTION));
  }
}
