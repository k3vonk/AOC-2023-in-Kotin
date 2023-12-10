plugins {
    kotlin("jvm") version "1.9.20"
    idea
    id("org.jlleitschuh.gradle.ktlint") version "12.0.2"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

ktlint {
    version.set("1.0.0")
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
