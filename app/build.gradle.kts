plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("scripting-jsr223"))
    implementation(kotlin("reflect"))
}

application {
    mainClass.set("test.AppKt")
    mainModule.set("test")
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}