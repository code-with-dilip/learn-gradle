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

## Give you plugin a id

-   We need to create this directory **META-INF/gradle-plugins** inside the resources directory

-   Create a properties file. The file name is your plugin id.
    -   For example, Create this file inside your resources directory - **com.firstplugin.greeting**
    -   The plugin id is  **com.firstplugin.greeting**
-   Provide the implementation-class inside the properties file.

    ```
    implementation-class=com.learnplugin.greeting.GreetingPlugin
    ```    
    -   This is the class where the plugin code is actually written.

## How do we test this plugin in another project(Before publishing it to Gradle)

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