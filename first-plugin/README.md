# Creating Custom Plugins

-   We need to include the **java-gradle-plugin** in plugin dsl as shown in the figure
```
plugins {
        id("java-gradle-plugin")
}
```

-   Add the below dependencies to make sure the gradle api and gradle test kit is applied to the project

```
dependencies {
    implementation(gradleApi())
    testImplementation(gradleTestKit())
}
```

##  Give you plugin a id

-   We need to create this directory **META-INF/gradle-plugins** inside the resources directory

-   Create a properties file. The file name is your plugin id.
    -   For example, Create this file inside your resources directory - **com.firstplugin.greeting**
    -   The plugin id is  **com.firstplugin.greeting**
-   Provide the implementation-class inside the properties file.

    ```
    implementation-class=com.learnplugin.greeting.GreetingPlugin
    ```    
    -   This is the class where the plugin code is actually written.

##  How do we test this plugin in another project(Before publishing it to Gradle)

-   First step is to provide the reference to the path where the plugin is located in your machine
    -   In the repositories, provide the **flatDir{}** to point to the folder in your local. 
    -   In the **dependencies** section, provide the **jar** file dependency.
    
- Example code below:
        
```aidl
buildscript{
    repositories {
        mavenCentral()
        flatDir {
            dirs ("/Users/z001qgd/Dilip/study/codewithdilip/learn-gradle/first-plugin/build/libs")
        }
    }
    dependencies {
        classpath("com.learnplugin:first-plugin-1.0-SNAPSHOT:1.0-SNAPSHOT")
    }
}

```

-   The next step is to apply the plugin:
```aidl
apply(plugin = "com.firstplugin.greeting")
```    

-   After this all the tasks will be visible in the new project where the above code is included.

##  How to pubish the Plugin

-   The very first thing that we need to do is the apply the **maven-publish** plugin like below

```
plugins {
        `maven-publish`
}
```
-   Adding the above plugin will enable new sets of tasks related to publishing the plugin.


### How to publish plugin to your local and validate 

-   Add the publishing task to the build file.

#### Approach - Publish to a directory in your machine
-   There is a task named **publish** which takes care of publishing to your local
-   The below config takes care of publishing it to the local repository. 
    -   As you can see the path given is **local directory**.

```aidl
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.learnplugin"
            artifactId = "first-plugin"
            version = "1.0-SNAPSHOT"

            from(components["kotlin"])
        }
    }
    repositories {
        maven {
            name = "myRepo"
            url = uri("file://${buildDir}/repo")
        }
    }

}
```

#### Approach - Publish to a mavenLocal in your machine

-   Run the task **publishToMavenLocal** 