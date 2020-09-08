object Dependencies {
    object Kotlin {
        val version = "1.4.0"
        val classpath = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"

        object Coroutines {
            val version = "1.2.2"
            val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        }
    }

    object Spigot {
        val annotations = "org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT"
        val repository = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"
    }

    object Paper {
        val version = "1.15.2-R0.1-SNAPSHOT"
        val api = "com.destroystokyo.paper:paper-api:$version"
        val repository = "https://papermc.io/repo/repository/maven-public/"
    }

    object WebSocket {
        val version = "1.3.8"
        val core = "org.java-websocket:Java-WebSocket:$version"
    }

    object SonaType {
        val repository = "https://oss.sonatype.org/content/groups/public/"
    }

    object JUnit {
        val core = "org.junit.jupiter:junit-jupiter:5.5.2"
    }

    object Retrofit {
        val version = "2.9.0"
        val core = "com.squareup.retrofit2:retrofit:$version"
        val moshi = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object OkHttp {
        val core = "com.squareup.okhttp3:okhttp:4.8.1"
    }

    object Moshi {
        val version = "1.10.0"
        val core = "com.squareup.moshi:moshi:$version"
        val kotlin = "com.squareup.moshi:moshi-kotlin:$version"
        val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }
}