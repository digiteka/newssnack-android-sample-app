import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.util.Locale
import java.util.Properties

plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
}

val versionMajor = 1
val versionMinor = 1
val versionPatch = 0
val versionBuild = 0

fun generateVersionCode() = versionMajor * 1_000_000 + versionMinor * 10_000 + versionPatch * 100 + versionBuild

fun generateVersionName(): String {
	val isRelease = gradle.startParameter.taskRequests.toString().lowercase(Locale.getDefault()).contains("release")
	val isProd = gradle.startParameter.taskRequests.toString().lowercase(Locale.getDefault()).contains("prod")

	// display build number except for prod release
	return if (isRelease || isProd) {
		"$versionMajor.$versionMinor.$versionPatch"
	} else {
		"$versionMajor.$versionMinor.$versionPatch-$versionBuild"
	}
}

val localProperties = Properties().apply {
	val localProperties = rootProject.file("local.properties")
	if (localProperties.exists() && localProperties.isFile) {
		InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
			load(reader)
		}
	} else {
		throw FileNotFoundException("local.properties file not found at ${localProperties.absolutePath}")
	}
}

android {
	namespace = "digiteka.videofeed.andro"
	compileSdk = 34

	defaultConfig {
		applicationId = "digiteka.videofeed.andro"
		minSdk = 23
		targetSdk = 34
		versionCode = generateVersionCode()
		versionName = generateVersionName()

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		buildConfigField("String", "DIGITEKA_VIDEOFEED_MDTK", "\"${localProperties.getProperty("DIGITEKA_VIDEOFEED_MDTK")}\"")
	}

	buildTypes {
		debug {
			isMinifyEnabled = false
			isDebuggable = true
		}
		release {
			isMinifyEnabled = true
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}

	buildFeatures {
		viewBinding = true
		buildConfig = true
	}
}

dependencies {

	implementation("androidx.core:core-ktx:1.13.1")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.11.0")
	debugImplementation("com.squareup.leakcanary:leakcanary-android:2.12")

	//Direct import from project
	implementation("com.github.digiteka:newssnack-android:1.1.0")
}