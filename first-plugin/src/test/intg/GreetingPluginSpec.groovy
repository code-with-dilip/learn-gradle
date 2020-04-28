import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification


class GreetingPluginSpec extends Specification{

    @Rule TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
        buildFile << """
            plugins {
                id 'com.firstplugin.greetingPlugin'
            }
        """

    }


    def "GreetingPlugin"() {

        given:
        def gradleRunner = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withDebug(true)
                .withArguments(Collections.singletonList("myTask"))

        when:
        def result = gradleRunner.build()
        println("result :  $result")

        then:
        result!=null
        result.tasks().forEach({
            println("it : $it")
        })
        println("out put is ${result.output}")
        result.task(":myTask").outcome == TaskOutcome.SUCCESS

    }
}
