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

  private InitAutoValueSourcesDir task;

  @BeforeMethod
  public void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(AutoValuePlugin.class)
    project.evaluate()

    task = project.tasks.initAutoValueSourcesDir as InitAutoValueSourcesDir
  }

  @Test
  void testCreateSourceFolders() {
    task.createSourceFolders()
    assertThat(project.sourceSets.generated, notNullValue())

    File javaDir = project.sourceSets.generated.java.srcDirs.getAt(0) as File
    assertThat(javaDir.name, equalTo("auto-value"))
  }

  @Test
  public void testGroup() {
    assertThat(task.group, equalTo(InitAutoValueSourcesDir.GROUP));
  }

  @Test
  public void testDescription() {
    assertThat(task.description, equalTo(InitAutoValueSourcesDir.DESCRIPTION));
  }
}
