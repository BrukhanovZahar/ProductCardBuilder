import com.google.protobuf.gradle.*

plugins {
    kotlin("jvm")                         version "2.1.20"
    kotlin("plugin.spring")               version "2.1.20"
    id("org.springframework.boot")        version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.google.protobuf")             version "0.9.4"
}

group   = "com.productcard"
version = "0.0.1-SNAPSHOT"
java { sourceCompatibility = JavaVersion.VERSION_21 }

repositories { mavenCentral() }

val grpcVersion       = "1.63.0"
val grpcKotlinVersion = "1.4.1"
val protobufVersion   = "3.25.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("io.grpc:grpc-netty-shaded:$grpcVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")

    compileOnly("org.apache.tomcat:annotations-api:6.0.53")
}

sourceSets {
    main {
        proto { srcDir("../proto") }
    }
}

protobuf {
    protoc { artifact = "com.google.protobuf:protoc:$protobufVersion" }
    plugins {
        id("grpc")   { artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion" }
        id("grpckt") { artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk8@jar" }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins { id("kotlin") }
        }
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}
