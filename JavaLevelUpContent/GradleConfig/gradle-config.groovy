// Root build.gradle
plugins {
    id 'org.springframework.boot' version '3.1.5' apply false
    id 'io.spring.dependency-management' version '1.1.3' apply false
    id 'java'
}

allprojects {
    group = 'com.taskmanagement'
    version = '0.1.0'
    
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply plugin: 'java'
    
    sourceCompatibility = '23'
    targetCompatibility = '23'
    
    tasks.withType(JavaCompile) {
        options.encoding = 'UTF-8'
    }
    
    test {
        useJUnitPlatform()
    }
}

// settings.gradle
rootProject.name = 'task-management'
include 'server', 'cli'

// server/build.gradle
plugins {
    id 'org.springframework.boot'
    id 'io.spring.dependency-management'
    id 'org.flywaydb.flyway' version '9.21.1'
}

dependencies {
    // Spring Boot
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    
    // Database
    implementation 'org.postgresql:postgresql'
    implementation 'org.flywaydb:flyway-core'
    
    // JWT & OAuth
    implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
    implementation 'com.google.api-client:google-api-client:2.2.0'
    
    // Test dependencies
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'
    testImplementation 'com.h2database:h2'
}

flyway {
    url = 'jdbc:postgresql://localhost:5432/taskmanagement'
    user = 'postgres'
    password = 'postgres'
    baselineOnMigrate = true
}

bootJar {
    archiveFileName = 'taskmanagement-server.jar'
}

// cli/build.gradle
plugins {
    id 'org.springframework.boot'
    id 'io.spring.dependency-management'
}

dependencies {
    // Spring Boot (lightweight subset for CLI)
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-webflux'
    
    // CLI specific dependencies
    implementation 'info.picocli:picocli-spring-boot-starter:4.7.4'
    
    // JWT & OAuth
    implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
    implementation 'com.google.api-client:google-api-client:2.2.0'
    
    // Test dependencies
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

bootJar {
    archiveFileName = 'taskmanagement-cli.jar'
}
