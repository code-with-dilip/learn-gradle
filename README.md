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


## Gradle Hands-On

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
