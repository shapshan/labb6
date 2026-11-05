plugins {
    java
    application
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("registration.Main")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
