package com.ewerk.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.WarPlugin
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.*
import static org.hamcrest.MatcherAssert.assertThat

/**
 * @author griffio
 */
public class DaggerJavaPluginTest {

  private Project project

  @BeforeMethod
  public void setup() {
    project = ProjectBuilder.builder().build()
    project.plugins.apply(DaggerPlugin.class)
    project.plugins.apply(WarPlugin.class)
  }

  @Test
  public void testBasePlugin() {
    assertThat(project.plugins.hasPlugin(BasePlugin.class), is(true))
  }

  @Test
  public void testPluginAppliesItself() {
    assertThat(project.plugins.hasPlugin(DaggerPlugin.class), is(true))
  }

  @Test
  public void testReApplyDoesNotFail() {
    project.plugins.apply(DaggerPlugin.class)
  }

  @Test
  public void testPluginAppliesJavaPlugin() {
    assertThat(project.plugins.hasPlugin(JavaPlugin.class), is(true))
  }

  @Test
  public void testPluginEvaluatesDependencies() {
    project.evaluate()
    def lib = project.configurations.compile.dependencies.collect {
      "$it.group:$it.name:$it.version" as String
    }.toSet()
    assertThat(lib, hasItem(project.extensions.dagger.library))
  }

  @Test
  public void testPluginEvaluatesCompileOptions() {
    project.evaluate()
    def args = project.tasks.compileDagger.options.compilerArgs as List
    assertThat(args, hasItems('-proc:only', '-s', '-processor', DaggerPluginExtension.PROCESSOR,
        new File(project.projectDir, DaggerPluginExtension.DEFAULT_DAGGER_SOURCES_DIR).path))
  }

  @Test
  public void testDefaultGeneratedSourcesDirIsSet() {
    assertThat(project.extensions.dagger.daggerSourcesDir as String,
        equalTo(DaggerPluginExtension.DEFAULT_DAGGER_SOURCES_DIR))
  }

  @Test
  public void testDefaultLibraryIsSet() {
    assertThat(project.extensions.dagger.library as String,
        equalTo(DaggerPluginExtension.DEFAULT_LIBRARY))
  }

  @Test
  public void testDefaultProcessorIsSet() {
    assertThat(project.extensions.dagger.processorLibrary as String,
        equalTo(DaggerPluginExtension.DEFAULT_PROCESSOR_LIBRARY))
  }
}