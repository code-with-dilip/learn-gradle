
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

apply(plugin = "com.firstplugin.greeting")

plugins {
    id ("org.jetbrains.kotlin.jvm") version "1.3.71"
    groovy
}

group =  "com.learngradle"
version  = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

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

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.apply {
    kotlinOptions.jvmTarget = "11"
    kotlinOptions.allWarningsAsErrors = true
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
}