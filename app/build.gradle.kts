plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    id("org.javamodularity.moduleplugin") version "1.8.12"
    id("org.beryx.jlink") version "2.25.0"
    application
}

group = "test"
version = "1.0.0"
val APP_NAME = "JSR223-Test"
val APP_VERSION = version.toString()
val OS: org.gradle.internal.os.OperatingSystem = org.gradle.internal.os.OperatingSystem.current()

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

jlink {
    launcher {
        name = APP_NAME
    }
    mergedModule {
        additive = true
        uses("org.jetbrains.kotlin.builtins.BuiltInsLoader")
        uses("org.jetbrains.kotlin.util.ModuleVisibilityHelper")
        uses("org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages.Extension")
        uses("org.jetbrains.kotlin.resolve.ExternalOverridabilityCondition")
        uses("org.jetbrains.kotlin.resolve.jvm.jvmSignature.KotlinToJvmSignatureMapper")
        uses("kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader")
        uses("kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper")
        uses("kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition")
    }
    jpackage {
        skipInstaller = true
        installerOptions = mutableListOf(
            "--name", APP_NAME,
            "--app-version", APP_VERSION,
            "--description", APP_NAME,
            "--copyright", "Copyright 2022 RationalityFrontline",
            "--vendor", "RationalityFrontline",
            "--verbose"
        )
        when {
            OS.isWindows -> {
                installerType = "msi"
                installerOptions.addAll(listOf(
                    "--win-per-user-install", "--win-dir-chooser",
                    "--win-menu", "--win-menu-group", APP_NAME,
                    "--win-shortcut", "--win-console",
                ))
            }
            OS.isLinux -> {
                installerType = "deb"
                installerOptions.addAll(listOf(
                    "--resource-dir", "build/jpackage/$APP_NAME/lib",
                    "--linux-package-name", APP_NAME,
                    "--linux-deb-maintainer", "rationalityfrontline@gmail.com",
                    "--linux-menu-group", APP_NAME,
                    "--linux-app-release", "1.0",
                    "--linux-shortcut"
                ))
            }
        }
    }
}