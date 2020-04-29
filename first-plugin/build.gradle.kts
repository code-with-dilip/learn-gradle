import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
val groovyVersion: String by project
val spockGroovyVersion: String by project


plugins {
        groovy
        kotlin("jvm") version "1.3.72"
        id("java-gradle-plugin")
        `maven-publish`
}

group = "com.learnplugin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.apache.httpcomponents:httpclient:4.5.5")
    implementation(gradleApi())
    testImplementation(gradleTestKit())
    testImplementation("junit:junit:4+")
    testImplementation("org.codehaus.groovy:groovy-all:$groovyVersion")
    testImplementation("org.spockframework:spock-core:$spockGroovyVersion")


}

sourceSets {
    test {
        withConvention(GroovySourceSet::class) {
            groovy {
                srcDirs(listOf("src/test/intg", "src/test/unit"))
            }
        }
    }
}
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

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}