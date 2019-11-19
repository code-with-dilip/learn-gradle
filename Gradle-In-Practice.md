# Build Script

- In gradle everything is a Project, Tasks and Properties.

## How Build Sript Works ?


-   Any build script go through initialization, configuration and execution phases.

### Gradle Tasks

-   To view the tasks in a project, run the below command.

```
gradle tasks --all
```

####  "ext"

-   Using **ext** we can add additional properties to the project.
-   The below code adds the firstName property   to the project.

```aidl
ext.firstName = 'Dilip'
```

#### dependsOn

-   This injects the dependency tasks that needs to be executed before the actual task.

-   The below example talks about injecting the dependency tasks.
```aidl
task helloWorld {
    doLast {
        println "Hello $firstName!"
    }
}

task hi(dependsOn: helloWorld){
    doLast {
        println "hi $firstName,from  doLast!"
    }
}

```

## Plugin

-   Gradle is a plugin based architecture.

### Java Plugin

```aidl
apply plugin: 'java'
```  

-   **apply** is a method call in the class and assigns the property **plugin** as **java**

#### Different phases of a Java Plugin

-   Below are the different phases the java plugin goes through when we **build** the project.  

```aidl
> Task :explore-java-plugin:compileJava NO-SOURCE
> Task :explore-java-plugin:processResources NO-SOURCE
> Task :explore-java-plugin:classes UP-TO-DATE
> Task :explore-java-plugin:jar UP-TO-DATE
> Task :explore-java-plugin:assemble UP-TO-DATE
> Task :explore-java-plugin:compileTestJava NO-SOURCE
> Task :explore-java-plugin:processTestResources NO-SOURCE
> Task :explore-java-plugin:testClasses UP-TO-DATE
> Task :explore-java-plugin:test NO-SOURCE
> Task :explore-java-plugin:check UP-TO-DATE
> Task :explore-java-plugin:build UP-TO-DATE
```