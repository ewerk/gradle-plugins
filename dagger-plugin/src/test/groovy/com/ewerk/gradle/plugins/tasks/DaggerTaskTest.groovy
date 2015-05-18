package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.DaggerPlugin
import com.ewerk.gradle.tasks.CleanDaggerSourcesDir
import com.ewerk.gradle.tasks.InitDaggerSourcesDir
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.notNullValue
import static org.hamcrest.MatcherAssert.assertThat

/**
 * @griffio
 */
class DaggerTaskTest {

  private Project project;

  private InitDaggerSourcesDir initTask;
  private CleanDaggerSourcesDir cleanTask;

  @BeforeMethod
  public void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(DaggerPlugin.class)
    project.evaluate()
    initTask = project.tasks.initDaggerSourcesDir as InitDaggerSourcesDir
    cleanTask = project.tasks.cleanDaggerSourcesDir as CleanDaggerSourcesDir
  }

  @Test
  void initSourceFolders() {
    initTask.createSourceFolders()
    assertThat(project.sourceSets.dagger, notNullValue())
    File javaDir = project.sourceSets.dagger.java.srcDirs.first() as File
    assertThat(javaDir.name, equalTo("java"))
  }

  @Test
  public void taskGroup() {
    assertThat(cleanTask.group, equalTo(DaggerPlugin.TASK_GROUP));
    assertThat(initTask.group, equalTo(DaggerPlugin.TASK_GROUP));
  }
}