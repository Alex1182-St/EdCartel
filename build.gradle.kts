import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "edcartel"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    jcenter()
    mavenCentral()

    maven(url = "https://repo.spring.io/snapshot")
    maven(url = "https://repo.spring.io/milestone")
}

plugins {
    val kotlinVersion = "1.3.61"
    val springBootVersion = "2.2.4.RELEASE"
    val dependencyManagementVersion = "1.0.9.RELEASE"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version dependencyManagementVersion

    kotlin(module = "jvm") version kotlinVersion
    kotlin(module = "plugin.spring") version kotlinVersion
    kotlin(module = "plugin.allopen") version kotlinVersion
    kotlin(module = "plugin.noarg") version kotlinVersion
    kotlin(module = "plugin.jpa") version kotlinVersion
}

allOpen {
    annotation(fqName = "javax.persistence.Entity")
    annotation(fqName = "javax.persistence.Embeddable")
    annotation(fqName = "javax.persistence.MappedSuperclass")
}

dependencies {
    implementation(kotlin(module = "noarg"))
    implementation(kotlin(module = "allopen"))
    implementation(kotlin(module = "reflect"))
    implementation(kotlin(module = "stdlib"))

    implementation(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin")
    implementation(group = "com.fasterxml.jackson.datatype", name = "jackson-datatype-hibernate5")

    implementation(group = "com.graphql-java-kickstart", name = "graphql-java-tools", version = "5.7.1")

    implementation(group = "com.graphql-java-kickstart", name = "graphql-spring-boot-starter", version = "6.0.1")
    testImplementation(group = "com.graphql-java-kickstart", name = "graphql-spring-boot-starter-test", version = "6.0.1")

    implementation(group = "org.springframework.boot", name = "spring-boot-devtools")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-actuator")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-security")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-data-jpa")

    annotationProcessor(group = "org.springframework.boot", name = "spring-boot-configuration-processor")
    testAnnotationProcessor(group = "org.springframework.boot", name = "spring-boot-configuration-processor")

    annotationProcessor(group = "org.projectlombok", name = "lombok")
    testAnnotationProcessor(group = "org.projectlombok", name = "lombok")

    runtimeOnly(group = "org.postgresql", name = "postgresql")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
