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

- The Java plugin adds Java compilation along with testing and bundling capabilities to a project

#### Apply Plugin
```aidl
apply plugin: 'java'
```  

-   **apply** is a method call in the class and assigns the property **plugin** as **java**
    -   The **plugin** is a map so the above call puts the java to the **plugin** map.

-   The below line sets the java source code compatibility to Java 8.

```aidl
sourceCompatibility = 1.8
```

-  Different phases of a Java Plugin

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

- Different Property values of a gradle file. Run the below command which will list all the properties that are applicable for a gradle file.

```
gradle properties
```
## Directed Acyclic Graph and Source Sets

### Source Set
- In general there are two different source sets available for a Java Plugin
  - main
    - Contains the production source code of the project, which is compiled and assembled into a JAR.
  - test
    - Contains your test source code, which is compiled and executed using JUnit

Check the following link - https://docs.gradle.org/current/userguide/java_plugin.html#source_sets


#### build.gradle

- Changing the **sourceSet** directory

```
sourceSets {
    main {
        java {
            srcDirs = ['src/main/java1', 'src/main/java']
        }
        resources {
            srcDirs = ['src/resources']
        }
    }
    test {
        java {
            srcDirs = ['src/test/java1', 'src/test/java']
        }
    }
}
```

#### build.gradle.kts

- Changing the **sourceSet** directory

```
sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/main/java","src/main/java1", "src/main/kotlin", "src/main/kotlin1"))
        }
        resources {
            setSrcDirs(listOf("src/resources"))
        }
    }
}
```

## Dependency Management

- Gradle has built in support for dependency management

- Dependencies are normally retrieved from repositories.

```
repositories {
    mavenCentral()
}
```

### Declaring Dependencies

- **Gradle** represents the scope of a dependency with the help of a Configuration. Every configuration can be identified by a unique name.

- In the below example implementation and testcompile are the confifurations.

```
dependencies {
    implementation 'org.apache.httpcomponents:httpclient:4.5.5'
    testCompile group: 'junit', name: 'junit', version: '4.12'
}
```

### Adding Custom Configurations

- The below piece of code add a custom configuration to the project

```
configurations {
    newConfiguration
}
```

- Using the custom configuration to pull the dependencies

```
dependencies {
    newConfiguration'org.apache.httpcomponents:httpclient:4.5.5'
}    
```

### Referencing dependencies in a specific path in your local

- The very first thing that needs to be done is to add the local machine directory in the **repositories** which is part of the Project Groovy API using the **flatDir**

```
flatDir {
        dirs '/Users/z001qgd/Dilip/study/codewithdilip/learn-gradle/explore-gradle-kotlindsl/build/libs'
    }
```
- The next step is to add the dependency configuration in the **dependencies** section.

```
implementation ("com.learngradle:explore-gradle-kotlindsl-1.0-SNAPSHOT:1.0-SNAPSHOT")
```
