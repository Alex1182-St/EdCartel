import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "edcartel"

java {
    sourceCompatibility = JavaVersion.VERSION_11
//    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    jcenter()
    mavenCentral()

    maven(url = "https://repo.spring.io/snapshot")
    maven(url = "https://repo.spring.io/milestone")
}

val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

plugins {
    val kotlinVersion = "1.3.61"
    val springBootVersion = "2.2.4.RELEASE"
    val dependencyManagementVersion = "1.0.9.RELEASE"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version dependencyManagementVersion

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    kotlin("plugin.noarg") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

extra["springBootAdminVersion"] = "2.2.1"
extra["springCloudVersion"] = "Hoxton.SR1"

dependencies {
    implementation(kotlin("noarg"))
    implementation(kotlin("allopen"))
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // GraphQL
    implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:6.0.1")
    testImplementation("com.graphql-java-kickstart:graphql-spring-boot-starter-test:6.0.1")

    // For dev
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Config
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-cache")

    // Security
    implementation("org.springframework.cloud:spring-cloud-starter-security")
    implementation("org.springframework.cloud:spring-cloud-starter-oauth2")

    // Database
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("org.postgresql:postgresql")

    // Admin
    implementation("de.codecentric:spring-boot-admin-starter-client")
    implementation("de.codecentric:spring-boot-admin-starter-server")

    implementation("io.micrometer:micrometer-registry-prometheus")

    // Lombok
    implementation("org.projectlombok:lombok")
}

dependencyManagement {
    imports {
        mavenBom("de.codecentric:spring-boot-admin-dependencies:${property("springBootAdminVersion")}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
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
