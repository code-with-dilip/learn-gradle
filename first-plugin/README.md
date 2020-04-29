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
    