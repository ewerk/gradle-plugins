package com.ewerk.gradle.plugins.tasks

import com.ewerk.gradle.plugins.Jaxb2Plugin
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.tasks.TaskAction

import static org.gradle.api.logging.Logging.getLogger

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
  private static final Logger LOG = getLogger(GenerateJaxb2Classes.class)

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
      def basePackage = theConfig.basePackage
      def encoding = theConfig.encoding

      // Transform package to directory location to specify depends/produces when multiple schema output to same generatedSourcesDir
      // Changing one schema will only cause recompilation/generation of that schema
      def generatedSourcesDirPackage = new File(generatedSourcesDirParent,
          basePackage.replace(".", "/"))

      def schemaFile = project.file(theConfig.schema)
      def catalogFile = project.file(theConfig.catalog)
      def bindingsDir = theConfig.bindingsDir
      def includedBindingFiles = bindingFileIncludes(theConfig)
      def extension = theConfig.extension
      def additionalArgs = theConfig.additionalArgs

      // the depends and produces is compared using the time-stamp of the schema file and the destination package folder
      ant.xjc(destdir: generatedSourcesDirParent,
          package: basePackage,
          schema: schemaFile,
          encoding: encoding,
          catalog: catalogFile,
          extension: extension,
          ) {
        depends(file: schemaFile)
        depends(file: catalogFile)
        produces(dir: generatedSourcesDirPackage, includes: "**/*.java")
        arg(line: additionalArgs)

        if (bindingsDir?.trim()) {
          binding(dir: project.file(bindingsDir), includes: includedBindingFiles)
        }
      }
    }
  }

  private static String bindingFileIncludes(XjcTaskConfig config) {
    def files = config.includedBindingFiles

    if (!files?.trim()) {
      LOG.info("No binding file includes defined, falling back to '**/*.xjb' pattern.")
      files = '**/*.xjb'
    }

    LOG.info("Binding files: {}", files)
    return files
  }
}
