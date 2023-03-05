import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
    testImplementation("org.seleniumhq.selenium:selenium-java:4.8.1")
    // https://mvnrepository.com/artifact/org.testng/testng
    testImplementation("org.testng:testng:7.7.1")
    // https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager
    testImplementation("io.github.bonigarcia:webdrivermanager:5.3.2")
    // https://mvnrepository.com/artifact/org.assertj/assertj-core
    testImplementation("org.assertj:assertj-core:3.24.2")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    testImplementation("org.slf4j:slf4j-api:2.0.6")
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    testImplementation("ch.qos.logback:logback-classic:1.4.5")
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-core
    testImplementation("ch.qos.logback:logback-core:1.4.5")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    // https://mvnrepository.com/artifact/com.github.javafaker/javafaker
    testImplementation("com.github.javafaker:javafaker:1.0.2")

    testCompileOnly("org.projectlombok:lombok:1.18.26")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.26")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useTestNG {
        suites(System.getProperty("suiteFile", "testng.xml") ?: "testng.xml")
    }

    systemProperties = System.getProperties().map { e -> Pair(e.key as String, e.value) }.toMap()

    testLogging {
        events(
            TestLogEvent.PASSED,
            TestLogEvent.FAILED,
            TestLogEvent.SKIPPED
        )
        exceptionFormat = TestExceptionFormat.FULL
    }
}
