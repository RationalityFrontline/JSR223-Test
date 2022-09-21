plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("scripting-jsr223"))
}

application {
    mainClass.set("test.AppKt")
}
