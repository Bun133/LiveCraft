plugins {
    java
    kotlin("jvm").version(Dependencies.Kotlin.version)
    kotlin("kapt").version(Dependencies.Kotlin.version)
}

group = "neocraft"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven(Dependencies.Spigot.repository)
    maven(Dependencies.Paper.repository)
    maven(Dependencies.SonaType.repository)
}

dependencies {
    compileOnly(Dependencies.Paper.api)
    compileOnly(Dependencies.Spigot.annotations)
    kapt(Dependencies.Spigot.annotations)
    compile(Dependencies.Kotlin.stdlib)
    compile(Dependencies.Kotlin.reflect)
    compile(Dependencies.Kotlin.Coroutines.core)
    compile(Dependencies.WebSocket.core)
    compile(Dependencies.Retrofit.core)
    compile(Dependencies.Retrofit.moshi)
    compile(Dependencies.OkHttp.core)
    compile(Dependencies.Moshi.core)
    compile(Dependencies.Moshi.kotlin)
    compile(Dependencies.Moshi.codegen)
    testCompile(Dependencies.JUnit.core)
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Kotlin.classpath)
    }
}

tasks {
    withType<Jar> {
        from(configurations.getByName("compile").map { if (it.isDirectory) it else zipTree(it) })
    }

    withType<Test>().configureEach {
        useJUnitPlatform()
    }
}