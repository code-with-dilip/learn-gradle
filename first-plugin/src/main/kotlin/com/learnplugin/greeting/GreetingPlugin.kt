package com.learnplugin.greeting

import com.learnplugin.tasks.MyTask
import org.gradle.api.Plugin
import org.gradle.api.Project

open class GreetingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.create("myTask", MyTask::class.java)
        project.task("hello") {
            it.doLast { it ->
                it.logger.quiet("Hello from the GreetingPlugin");
            }
        }
    }
}

