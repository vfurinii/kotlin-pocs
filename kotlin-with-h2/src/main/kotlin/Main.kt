import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class H2ExampleApp

fun main(args: Array<String>) {
    runApplication<H2ExampleApp>(*args)
}