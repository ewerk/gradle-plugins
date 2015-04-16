package com.ewerk.gradle.plugins.tasks;

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.api.tasks.SourceTask
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import com.ewerk.gradle.plugins.QuerydslPlugin

public class QuerydslCompileTest {
    private Project project;

    private SourceTask compileTask;


    public QuerydslCompileTest() {
	project = ProjectBuilder.builder().build();
	project.plugins.apply(QuerydslPlugin.class)
	compileTask = project.tasks.compileQuerydsl as SourceTask
	compileTask.includes += ['**/entities/*.java']
	project.evaluate()

	
    }
    
    @Test
    void tesIncludes() {
	def includes = compileTask.getIncludes()
	assertThat(compileTask.getIncludes(), hasItem(is('**/entities/*.java')))
    }
}