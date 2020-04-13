import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "edcartel"

val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    jcenter()
    mavenCentral()

    maven(url = "https://repo.spring.io/snapshot")
    maven(url = "https://repo.spring.io/milestone")
}

plugins {
    val kotlinVersion = "1.3.70"
    val springBootVersion = "2.2.6.RELEASE"
    val dependencyManagementVersion = "1.0.9.RELEASE"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version dependencyManagementVersion

    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.noarg") version kotlinVersion
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-allopen")
    implementation("org.jetbrains.kotlin:kotlin-noarg")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.cloud:spring-cloud-starter-security")
    implementation("org.springframework.cloud:spring-cloud-starter-oauth2")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    runtimeOnly("org.postgresql:postgresql")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR3")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}