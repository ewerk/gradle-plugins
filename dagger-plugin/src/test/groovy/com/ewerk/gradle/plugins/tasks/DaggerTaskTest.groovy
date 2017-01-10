package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.DaggerPlugin
import org.gradle.api.GradleException
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

  private Project project

  private InitDaggerSourcesDir initTask
  private CleanDaggerSourcesDir cleanTask

  @BeforeMethod
  void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(DaggerPlugin.class)
    project.evaluate()
    initTask = project.tasks.initDaggerSourcesDir as InitDaggerSourcesDir
    cleanTask = project.tasks.cleanDaggerSourcesDir as CleanDaggerSourcesDir
  }

  @Test(expectedExceptions = GradleException.class, expectedExceptionsMessageRegExp = "The configured daggerSourcesDir.*")
  void testCreateSourceFoldersException() {
    project.dagger.daggerSourcesDir = "src/main/java"
    initTask.createSourceFolders()
  }

  @Test(expectedExceptions = GradleException.class, expectedExceptionsMessageRegExp = "The configured daggerSourcesDir.*")
  void testCleanSourceFoldersException() {
    project.dagger.daggerSourcesDir = "src/main/java"
    cleanTask.cleanSourceFolders()
  }

  @Test
  void initSourceFolders() {
    initTask.createSourceFolders()
    assertThat(project.sourceSets.dagger, notNullValue())
    File javaDir = project.sourceSets.dagger.java.srcDirs.first() as File
    assertThat(javaDir.name, equalTo("java"))
  }

  @Test
  void taskGroup() {
    assertThat(cleanTask.group, equalTo(DaggerPlugin.TASK_GROUP))
    assertThat(initTask.group, equalTo(DaggerPlugin.TASK_GROUP))
  }
}