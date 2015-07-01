package com.ewerk.gradle.plugins

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.is
import static org.hamcrest.MatcherAssert.assertThat

/**
 * @author griffio
 *
 *
 */
public class DaggerAndroidPluginTest {

  private Project project

  @BeforeMethod
  public void androidProject() {
    Project root = ProjectBuilder.builder().build();
    project = ProjectBuilder.builder().withParent(root).build()
    project.plugins.apply('android-sdk-manager')
    project.plugins.apply('android')
    project.plugins.apply(DaggerPlugin.class)
    //BasePlugin,init SdkHandler
    project.android {
      compileSdkVersion "android-21"
      buildToolsVersion "21.1.2"
      defaultConfig {
        minSdkVersion 15
        targetSdkVersion 21
        versionCode 1
        versionName "1.0"
      }
    }

    project.repositories {
      mavenCentral()
    }

  }

  @Test
  public void testPlugins() {
    assertThat(project.plugins.hasPlugin(AppPlugin) || project.plugins.hasPlugin(LibraryPlugin), is(true))
  }

  @Test
  public void testPluginDependencies() {
    def lib = project.configurations.compile.dependencies.collect {
      "$it.group:$it.name:$it.version" as String
    }.toSet()
    assertThat(lib, hasItem(project.extensions.dagger.library))
  }

  @Test
  public void testVariant() {
    project.evaluate()
    project.android.applicationVariants.all { v ->
      println(v.dirName)
    }
  }

}