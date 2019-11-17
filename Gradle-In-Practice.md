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