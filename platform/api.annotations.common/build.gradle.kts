plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

tasks {
    val prepareSources by registering(Copy::class) {
        from("src")
        include("**/*.java")
        exclude("main/**")

        includeEmptyDirs = false

        into("src/main/java")
    }

    val prepareResources by registering(Copy::class) {
        from("src")
        exclude("**/*.java", "main/**")

        includeEmptyDirs = false

        into("src/main/resources")
    }

    compileJava.get().dependsOn(prepareSources, prepareResources)
    clean.get().delete.add("src/main")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")

    api("org.apache.commons:commons-math3:3.6.1")

    implementation("com.google.guava:guava:30.1-jre")
}

tasks.test {
    useJUnitPlatform()
}