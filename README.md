# Introduction to Project Automation

## Build Tool
- A build tool allow you to express your needs as executable or ordered tasks in order to create a repeatable, reliable, and portable build without manual intervention.
  - Compile code
  - Copy class files to a directory
  - Assemble the deliverable.
- In the above listed tasks except the first one the other tasks are dependent on each other and they are called Directed Acyclic graph(DAG) or Task Dependencies.

## Anatomy of a build tool

- The actual definition of the build tool sits in the **build** file.

**Build engine**
- The build file's step by step instruction is translated in to an internal model the build tool can understand.
- The build engine processes the build at run time.

**Dependency Manager**

- This allows you to declare the dependencies thats needed for the project.
- Normally these dependencies can be retrieved from a file system , http or FTP.
- Many libraries depend on other libraries, called transitive dependencies.

## Exploring Ant and Maven

### Ant
- The build file is expressed through XML, which makes it portable across different runtime environments.
- The build script consists of the below basic elements
  - Project
  - Target
  - Task
- The book has a really good explanation of how **Ant** works. Please go through the same.

**Ant's ShortComings**  
- Using XML as the definition language for your build logic results in overly large and verbose build.
- Complex build logic leads to long and unmaintainable build scripts.
- Ant doesn’t give you any guidelines on how to set up your project.
- Ant doesn’t give you any guidelines on how to set up your project.
- Using Ant without Ivy makes it hard to manage dependencies.
- Using Ant across many projects within an enterprise has a big impact on maintainability.

### Maven
- The Maven team realized the need for a standardized project layout and unified build lifecycle.
- Maven picks up on the idea of convention over configuration,meaning that it provides sensible default values for your project configuration and its behavior.
- Maven’s core functionality can be extended by custom logic developed as plugins.
- Maven is based on the concept of a build lifecycle.
  - Every step in this build lifecycle is called a **phase**. Phases are executed sequentially.
  - Maven will automatically determine that the dependent phases like source code compilation and running tests need to be executed beforehand.
-  Maven preconfigures the use of the repository, **Maven Central**, to download dependencies.
  - Subsequent builds will reuse an existing artifact from the local cache and therefore won’t contact Maven Central.

**Maven's ShortComings**  
- Maven proposes a default structure and lifecycle for a project that often is too restrictive and may not fit your project’s needs.
- Writing custom extensions for Maven is overly cumbersome.
  - You’ll need to learn about Mojos (Maven’s internal extension API), how to provide a plugin descriptor.
  -

### Requirements for a next-generation build tool  
- Expressive, declarative, and maintainable build language.
- Standardized project layout and lifecycle, but full flexibility and the option to fully configure the defaults.
- Support for dependency management.
- Emphasis on scalable and high-performance builds. This will matter if you have long-running builds (for example, two hours or longer), which is the case for some big enterprise projects.

#2 - Next-generation builds with Gradle
- Gradle is the next evolutionary step in JVM-based build tools.
- Gradle allows for declaratively modeling your problem domain using a powerful and expressive domain-specific language (DSL) implemented in Groovy instead  of XML.

## 2.1. WHY GRADLE? WHY NOW?

- Read the Complete **2.1** section.

### Why you should choose Gradle

- It’s impressive to see how much less code you need to write in Gradle to achieve the same goal.
- Gradle doesn’t force you to fully migrate all of your existing build logic. Good integration with other tools like Ant and Maven is at the top of Gradle’s priority list.

## 2.2. GRADLE’S COMPELLING FEATURE SET

- A build script directly maps to an instance of type Project in Gradle’s API.
- Each element in a Gradle script has a one-to-one representation with a Java class.
- Gradle describes this unit of work as a task.
- Being able to use a programming language to express your build needs is a major plus.

Gradle Build Language Reference Guide - http://www.gradle.org/docs/current/dsl/index.html  
### 2.2.3. Flexible conventions
- One of Gradle’s big ideas is to give you guidelines and sensible defaults for your projects
- Gradle’s conventions are similar to the ones provided by Maven, but they don’t leave you feeling boxed in. Maven is very opinionated; it proposes that a project only contains one Java source directory and only produces one single         JAR file. This is not necessarily reality for many enterprise projects.

### 2.2.4. Robust and powerful dependency management
-  External dependencies are accessible through repositories, and the type of repository is highly dependent on what your company prefers. Options range from a plain file system to a full-fledged enterprise repository.
- Gradle provides an infrastructure to manage the complexity of resolving, retrieving, and storing dependencies. Once they’re downloaded and put in your local cache, they’re made available to your project.
- Dependency managers like Ivy and Maven in their current implementation cannot fully guarantee reproducibility. Why is that? Whenever a dependency is downloaded and stored in the local cache, it doesn’t take into account the artifact’s origin.In situations where the repository is changed for a project, the cached dependency is considered resolved, even though the artifact’s content may be slightly different.

### 2.2.6. Effortless extendibility
- The easiest way to implement custom logic is by writing a task. Tasks can be defined directly in your build script without special ceremony.
- If you want to share reusable code among builds and projects, plugins are your best friend.

### 2.2.7. Integration with other build tools
- Gradle builds are 100% compatible with Maven and Ivy repositories.
- Gradle provides a converter for existing Maven builds that can translate the build logic into a Gradle build script.

### Gradle Wrapper
- Gradle Wrapper to the rescue! It allows for downloading and installing a fresh copy of the Gradle runtime from a specified repository on any machine you run the build on.
- This process is automatically triggered on the first execution of the build.

**When to use the wrapper**  
- Using the wrapper is considered best practice and should be mandatory for every Gradle project. Gradle scripts backed by the wrapper are perfectly prepared to run as part of automated release processes like continuous integration and delivery.


## Chapter 3 : Build a Project by Example

## Upgrading Gradle

- The below command will upgrade grale.

```
./gradlew wrapper --gradle-version=5.4.1
```

### Executing a task in Gradle
- Create a file named **build.gradle**

```
task helloWorld {
   doLast {
      println 'Hello world!'
   }
}
```

- Run the task using the below command. I have both the options listed below.
**option1**  
```
gradle -q helloWorld
```
**option2**  
```
gradle -q hW
```

### dependsOn

- DependsOn makes sure that the task that it depends on gets executed before the actual task.

```
yayGradle0.dependsOn startSession
```

## Plugin

### Java

**Approach 1**

```plugins {
    id 'java'
}
```

**Approach 2**

```
apply plugin: 'java'
```
- One of the task the Java plugin adds to the project is the **build** task.

#### jar

- By definiing the jar task like below we are overriding the default behavior of gradle.
```
jar {
    manifest {
        attributes 'Main-Class': 'com.learngradle.ToDoApp'
    }
}
```

## 4. Build script essentials
### Nutshell
  - A build script has
    - Project - apply, buildScript,dependencies, configurations, tasks, file, files, name , version and more.
    - Task  - dependsOn, inputs, outputs, ant, description, finalizedBy.
  - Phases of build
    - Intialization
    - Configuration
    - Execution
- Every Gradle build consists of three basic building blocks: projects, tasks, and properties.
- Each build contains at least one project, which in turn contains one or more tasks.
  - Project and Tasks have a direct class representation in Gradle’s API

### Projects
- In Gradle’s terminology a project represents a component you’re trying to build (for example, a JAR file), or a goal you’re trying to achieve, like **deploying an application**.
- When starting the build process, Gradle instantiates the class org.gradle.api.Project based on your configuration in build.gradle and makes it implicitly available through the **project variable**.

```
def name = getName() // gets access to the project instance and calls the name of the project.
println name
```
- Keep in mind that you’re not required to use the project variable when accessing properties and methods of your project—it’s assumed you mean the Project instance.

#### Extra properties

 -  To add properties, you’re required to use the **ext** namespace.

 **Approach 1**
```
project.ext.prop = 123
```
**Approach 2**
 ```
 ext {
   someOtherprop = 'Hello'
 }
 ```

 - Access and print them using the below syntax.

 ```
println ext.prop
println ext.someOtherprop
 ```

- You can also add properties by adding the **gradle.properties** to the root path.

- There are other ways to declare new properties.
  - Project property via the –P command-line option
    ```
    build -Pmessage=HelloFromCommandLine
    ```
  - System property via the –D command-line option
    ```
    build -Dmessage=HelloFromCommandLine
    ```
  - Setting a Project property using an environment variable.
  ```
  Setting a project property via a system property
    org.gradle.project.foo=bar
  Setting a project property via an environment variable
    ORG_GRADLE_PROJECT_foo=bar
  ```

### WORKING WITH TASKS  
- By default, every newly created task is of type org.gradle.api.DefaultTask, the standard implementation of org.gradle.api.Task.
- All fields in class DefaultTask are marked private.
- Task has two methods in it.
  - doFirst
  - doLast
- Example of a customTask  
```
task printVersion{
    doFirst {
        println "doFirst of printVersion"
        doMiddle("$version")
    }

    doLast {
        println "version is $version"
    }
}
def doMiddle(version) {
    println "doMiddle of printVersion $version"
}
```  
- **group** and **description**. Both act as part of the task documentation.

```
task printVersion(group: 'versioning', description: 'Prints Version of the project.'){
    doFirst {
        println "doFirst of printVersion"
        doMiddle("$version")
    }

    doLast {
        println "version is $version"
    }
}
```
-  By running **gradle tasks** you will be group information displayed in the console like below.

```
Versioning tasks
----------------
printVersion - Prints Version of the project.

```

##### dependsOn

- The **dependsOn** can be done in two different ways.
  - By Passing the **dependsOn** to a task method.
  - By specifying the **dependsOn** after the **dot** operator.
```
task first {
  doLast {
  println 'first'
  }
}

task second {
  doLast {
  println 'second'
  }
}

task third(dependsOn: [first,second]){
  doLast {
  println 'third'
  }
}

task four {
  doLast {
  println 'four'
  }
}

four.dependsOn third
```

#### finalizedBy

- You may find yourself in situations that require a certain resource to be cleaned up after a task that depends on it is
 executed.

```
task first {
  doLast {
  println 'first'
  }
}

task second {
  doLast {
  println 'second'
  }
}

first.finalizedBy second
```

- Executing the gradle task using the below command.

```
gradle -q first
```

#### Add a class to build.gradle

- You have the flexibility to add class files to the **build.gradle** file which will allow you to customize the code logic.
- Check this in the **todo-app** project.

```
version = new ProjectVersion(0,2,true)

class ProjectVersion{
    Integer minor
    Integer major
    Boolean releaseFlag

    ProjectVersion(Integer minor, Integer major, Boolean releaseFlag) {
        this.minor = minor
        this.major = major
        this.releaseFlag = releaseFlag
    }

    ProjectVersion(Integer minor, Integer major) {
        this.minor = minor
        this.major = major
    }

    @Override
    String toString() {
        return "$major.$minor${releaseFlag ? '' : '-SNAPSHOT'}"
    }
}
```

-  Now its time to externalize the version properties.

```
ext.versionFile = file('version.properties')
task loadVersion {
    project.version=readVersion() // This is a configuration. So this gets executed even though the task is not specifically invoked.
    println "Version is $project.version"

}

ProjectVersion readVersion() {

    logger.quiet('Inside readVersion')

    Properties versionProps = new Properties()
    versionFile.withInputStream {
        stream -> versionProps.load(stream)
    }

    new ProjectVersion(versionProps.major.toInteger(), versionProps.minor.toInteger()
                 ,versionProps.release.toBoolean())
}
```

#### Gradle’s build lifecycle phases
There are three different phases for a **gradle** lifecycle
  - Intialization
    - Gradle creates a Project instance for your project.
  - build phase
    - This is only applicable for multiproject builds.
  - Configuration
    - Gradle constructs a model representation of the tasks that will take part in the build.
    - Any configuration code is executed with every build of your project—even if you just execute gradle tasks.
    - The incremental build feature determines if any of the tasks in the model are required to be run.
  - Execution
    - In the execution phase tasks are executed in the correct order. The execution order is determined by their dependencies.

#### Declaring task inputs and outputs
- Gradle determines if a task is up to date by comparing a snapshot of a task’s inputs and outputs between two builds.
- Remember, task inputs and outputs are evaluated during the configuration phase to wire up the task dependencies. That’s why they need to be defined in a configuration block.
- The task gets executed when either the inputs or output change.
  - When there is no change to the input/output you will get below.
    ```
    1 actionable task: 1 up-to-date
    ```
  - When there is a change to the input/output you will get below.
    ```
    1 actionable task: 1 executed
    ```      

```
task releaseVersion {
    inputs.property('releaseFlag', version.releaseFlag) //you have the option to set the input value to the task
    outputs.file versionFile
    doLast {
        version.releaseFlag = true
        ant.propertyfile(file: versionFile) {
            entry(key: 'releaseFlag', type: 'string', operation: '=', value: 'true')
        }
    }
}
```
#### Writing a Custom Task

- The same thing above can be expressed like above.
- The input and output can be expressed using the **@Input** and **@OutputFile** annotations.
- The gradle configuration keeps track of changes whether to run the build or not using the input and outputs.

```
// Creating a custom task

class ReleaseVersionTask extends DefaultTask {

    @Input Boolean releaseFlag
    @OutputFile File destFile

    @TaskAction
    void start(){
        project.version.releaseFlag = true
        ant.propertyfile(file: destFile) {
            entry(key: 'releaseFlag', type: 'string', operation: '=', value: 'false')
        }
    }
}

// This is how we pass the input to the ReleaseVersionTask that's created.
task releaseVersion1(type: ReleaseVersionTask) {
    releaseFlag = version.releaseFlag
    destFile = versionFile
}

```

#### Gradle’s built-in task types

## 5. Dependency management
### Nutshell
- Grade DSL Configuration Closures makes it easy to retrieve the dependencies.
  - dependencies closure.
  - repositories closure.
- Project API
    - Has one Congifuration Container
      - The Configuration Container can have n number of Configurations.
- The Configuration for a Java plugin is - compile, runtime, testCompile, testRuntime, archives, and default.
- dependecies is represnted to DependecyHandler
  - Each dependency is of type Dependency. The attributes group, name, version clearly identify a dependency.
  - Shortcut to declare a dependency is "groupname:modulename:version"  
  - You have the option to exclude the transitive dependency using a simple syntax.


#### Introduction
- Gradle’s DSL configuration closures make it easy to declare **dependencies** and the **repositories** to retrieve them from.
- You define what libraries your build depends on with the dependencies script.
- Second, you tell your build the origin of these dependencies using the repositories closure.
- Dependency management sounds like an easy nut to crack, but can become difficult when it comes to dependency resolution conflicts.         
- Transitive dependencies, the dependencies a declared dependency relies on, can be a blessing and a curse.

**Imperfect dependency management techniques**
- Manually copying JAR files to the developer machine
- Using a shared storage for JAR files
- Checking JAR files that get downloaded with the project source code into the VCS.

**Importance of automated dependency management**
- Knowing the exact version of a dependency.
- Transitive dependencies are of concern even at an early stage of development.These are the libraries your first-level dependencies require in order to work correctly.

**Using automated dependency management**
- The dependency identifiers plus their respective versions, and
- The location of the binary repositories (for example, an HTTP address you want to retrieve them from).
- Using the above information the dependency manager will retrieve the dependencies and store them in their local cache.
- Using a dependency manager frees you from the burden of manually having to copy or organize JAR files.
- Gradle provides a powerful out-of-the-box dependency management implementation.

### LEARNING DEPENDENCY MANAGEMENT BY EXAMPLE
- Dependency management is done by using the two configuration blocks.
  - repositories
  - depedencies
- The names of the configuration blocks directly map to methods of the interface Project.  

### DEPENDENCY CONFIGURATIONS
- Plugins can introduce configurations to define the scope for a dependency.
  - **Java plugin** brings in a variety of standard configurations to define which bucket of the Java build lifecycle a dependency should apply to. For example, dependencies required for compiling production source code are added with the compile configuration.

#### Understanding the configuration API representation
- Configurations can be directly added and accessed at the root level of a project.
- Every project owns a container of class ConfigurationContainer that manages the corresponding configurations.  
- For Example, the **java** plugin comes up with these configurations out of the box.
  ```
  compile, runtime, testCompile, testRuntime, archives, and default
  ```
- You can add your own configurations using the below closure.

```
configurations {

}
```  
-  Check the section **5.3** in the book.

### DECLARING DEPENDENCIES

- Every Gradle project has an instance of a dependency handler.
- You obtain the reference of the **DependencyHandler** using the dependencies configuration block.
-  Each dependency is an instance of type Dependency. The attributes group, name, version, and classifier clearly identify a dependency.

#### Excluding Transitive Dependencies

- Syntax to exclude the transitive dependencies.
  - **exclusion** attributes are slightly different from the regular dependency notation.
  - You can use the attributes group and/or module. Gradle doesn’t allow you to exclude only a specific version of a dependency, so the version attribute isn’t available.                  
```
dependencies {
    compile group: 'org.apache.commons', name: 'commons-lang3', version: '3.1'
    testCompile(group: 'junit', name: 'junit', version: '4.12') {
        exclude (group: 'org.hamcrest', module :'hamcrest-core')
    }
}
```
- Excluding all the transitive dependencies.

```
dependencies {
    compile group: 'org.apache.commons', name: 'commons-lang3', version: '3.1'
    testCompile(group: 'junit', name: 'junit', version: '4.12') {
        //exclude (group: 'org.hamcrest', module :'hamcrest-core')
        transitive = false
    }
}
```

### USING AND CONFIGURING REPOSITORIES

- Central to defining repositories in your project is the interface **RepositoryHandler**.
- From the project, these methods are invoked within your repositories configuration block.

repositories {
    mavenCentral() // -> http://repo1.maven.org/maven2
    jcenter()
    maven {
      name 'Custom Maven Repsitory'
      url 'http://repository-gradle-in-action.forge.cloudbees.com/release/'
    }
}

### UNDERSTANDING THE LOCAL DEPENDENCY CACHE

-  Dependencies are downloaded and cached in this path **/Users/<USER_ID>/.gradle/caches/**.

#### Notable caching features
- The real power of Gradle’s cache lies in its metadata.
  - Storing the origin of a dependency
  - Artifact change detection
  - Reduced artifact downloads and improved change detection
    - Gradle detects if an artifact was changed in the repository by comparing its local and remote checksum. Unchanged artifacts are not downloaded again and reused from the local cache.
    - Offline mode
      -  Suppose lets say you are traveling and don't have the internet connection then run the build using the offline will avoid chekcing the remote repositories.

### TROUBLESHOOTING DEPENDENCY PROBLEMS      
- Gradle’s default strategy to resolve those conflicts is to pick the newest version of a dependency.
-
