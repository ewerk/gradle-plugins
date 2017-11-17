package com.ewerk.gradle.plugins

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.*
import static org.hamcrest.MatcherAssert.assertThat

/**
 * @author griffio
 *
 * */
class DaggerAndroidPluginTest {

  private Project project
  private File generatedDir

  @BeforeMethod
  void androidProject() {
    Project root = ProjectBuilder.builder().build()

    project = ProjectBuilder.builder().withParent(root).build()
    project.plugins.apply('android')
    project.plugins.apply(DaggerPlugin.class)
    project.android {
      compileSdkVersion "android-25"
      buildToolsVersion "25.0.2"
      defaultConfig {
        testApplicationId "com.ewerk.gradle.plugins"
        minSdkVersion 15
        targetSdkVersion 25
        versionCode 1
        versionName "1.0"
      }
    }

    project.repositories {
      mavenCentral()
    }

    generatedDir = new File(project.projectDir, "/build/generated/source/dagger")
  }

  @Test
  void testPlugins() {
    assertThat(project.plugins.hasPlugin(AppPlugin) || project.plugins.hasPlugin(LibraryPlugin), is(true))
  }

  @Test
  void testPluginDependencies() {
    def lib = project.configurations.compile.dependencies.collect {
      "$it.group:$it.name:$it.version" as String
    }.toSet()
    assertThat(lib, hasItem(project.extensions.dagger.library))
  }

  @Test
  void testVariant() {
    project.evaluate()
    project.android.applicationVariants.all { variant ->
      def args = variant.javaCompile.options.compilerArgs as List
      assertThat(args, hasItems('-s', '-processor', DaggerPluginExtension.PROCESSOR,
          new File(generatedDir, variant.dirName as String).path))
    }

  }
}
