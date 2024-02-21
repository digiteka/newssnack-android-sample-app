import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.util.Properties

pluginManagement {
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
	}
}
dependencyResolutionManagement {
	val localProperties = Properties().apply {
		val localProperties = File(rootProject.projectDir, "local.properties")
		if (localProperties.exists() && localProperties.isFile) {
			InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
				load(reader)
			}
		} else {
			throw FileNotFoundException("local.properties file not found at ${localProperties.absolutePath}")
		}
	}
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
		mavenLocal()

		maven {
			url = uri("https://jitpack.io")
			credentials {
				username = localProperties.getProperty("DIGITEKA_VIDEOFEED_SECRET")
			}
		}
	}
}

rootProject.name = "NewsSnack"
include(":sample")