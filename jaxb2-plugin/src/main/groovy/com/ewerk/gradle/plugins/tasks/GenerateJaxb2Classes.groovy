package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.Jaxb2Plugin
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Task that does the actual generation stuff. Declares the Ant task and then runs it for all
 * configured {@link XjcTaskConfig} objects.
 *
 * xjc https://jaxb.java.net/2.2.4/docs/xjcTask.html
 * depends/produces is used for incremental compilation
 *
 * @author holgerstolzenberg
 * @since 1.0.0
 */
class GenerateJaxb2Classes extends DefaultTask {
  GenerateJaxb2Classes() {
    this.group = Jaxb2Plugin.TASK_GROUP
  }

  @SuppressWarnings("GroovyUnusedDeclaration")
  @TaskAction
  antXjc() {
    ant.taskdef(
        name: 'xjc',
        classname: project.extensions.jaxb2.taskName,
        classpath: project.configurations.jaxb2.asPath)

    Set<XjcTaskConfig> xjcConfigs = project.extensions.jaxb2.xjc

    for (XjcTaskConfig theConfig : xjcConfigs) {

      def generatedSourcesDirParent = project.file(theConfig.generatedSourcesDir)
      // Transform package to directory location to specify depends/produces when multiple schema output to same generatedSourcesDir
      // Changing one schema will only cause recompilation/generation of that schema
      def generatedSourcesDirPackage = new File(generatedSourcesDirParent,
          theConfig.basePackage.replace(".", "/"))

      def schemaFile = project.file(theConfig.schema)
      // the depends and produces is compared using the time-stamp of the schema file and the destination package folder
      ant.xjc(destdir: generatedSourcesDirParent,
          package: theConfig.basePackage,
          schema: schemaFile) {
        depends(file: schemaFile)
        produces(dir: generatedSourcesDirPackage, includes: "**/*.java")
      }
    }
  }
}
