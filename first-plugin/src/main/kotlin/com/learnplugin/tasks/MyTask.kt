package com.learnplugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class MyTask : DefaultTask() {

    @TaskAction
    fun  myTask(){
        //println("Inside myTask")
        logger.quiet("Inside myTask");

    }
}